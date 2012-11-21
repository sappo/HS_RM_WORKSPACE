package com.ks.zk.zkserver;

import com.ks.zk.zkserver.util.SocketStatus;
import com.ks.zk.zkserver.util.HandlerStatus;
import com.ks.zk.zkserver.util.ReflectionUtils;
import com.ks.zk.zkserver.util.ServerMethod;
import com.ks.zkinterface.ZKServer;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Kevin Sapper (2012)
 */
public class ClientHandler implements Runnable {

    private Charset charset = Charset.forName("ISO-8859-1");

    private Socket client;

    private ZKServer server;

    private HandlerStatus status;

    private SocketStatus socketStatus;

    private Method callMethod;

    private Class<?>[] methodParamsTypes;

    private Object[] methodParams;

    private static final char DELIMITER = ';';

    public ClientHandler(Socket client, ZKServer server) {
        this.client = client;
        this.server = server;
        this.status = HandlerStatus.METHOD;
        this.socketStatus = SocketStatus.READ;
        methodParams = ArrayUtils.EMPTY_OBJECT_ARRAY;
    }

    @Override
    public void run() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        StringBuffer paramBuffer = new StringBuffer(1024);
        try {
            //create channel for input stream
            SocketChannel socketChannel = client.getChannel();
            while (socketChannel.read(byteBuffer) != -1) { // read until socket closes
                byteBuffer.flip(); // switch buffer to read mode

                do {
                    read(paramBuffer, byteBuffer);
                    // @todo: remove, this is test code for telnet
                    paramBuffer = new StringBuffer(StringUtils.replace(paramBuffer.toString(), "\r\n", ""));
                    if (SocketStatus.READ_DONE.equals(socketStatus)) {
                        switch (status) {
                            case METHOD:
                                for (Method method : ReflectionUtils.getMethods(server, ServerMethod.class)) {
                                    if (StringUtils.equals(method.getName(), paramBuffer.toString())) {
                                        callMethod = method;
                                        methodParamsTypes = method.getParameterTypes();
                                        if (methodParamsTypes.length > 0) {
                                            status = HandlerStatus.PARAM;
                                        } else {
                                            status = HandlerStatus.EXEC;
                                        }
                                        continue;
                                    }
                                }
                                if (callMethod == null) {
                                    // reset
                                    byteBuffer.position(byteBuffer.limit());
                                    String msg = "[ERROR] Invalid function name\n";
                                    ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes(charset));
                                    socketChannel.write(outBuffer);
                                }
                                paramBuffer = new StringBuffer(1024);
                                break;
                            case PARAM:
                                Class<?> methodParamType = methodParamsTypes[0];
                                if (methodParamType.equals(String.class)) {
                                    methodParams = ArrayUtils.add(methodParams, paramBuffer.toString());
                                } else if (methodParamType.equals(int.class)) {
                                    Integer value = new Integer(paramBuffer.toString());
                                    methodParams = ArrayUtils.add(methodParams, value);
                                }
                                methodParamsTypes = (Class<?>[]) ArrayUtils.remove(methodParamsTypes, 0);
                                paramBuffer = new StringBuffer(1024);
                                if (methodParamsTypes.length == 0) {
                                    status = HandlerStatus.EXEC;
                                }
                                break;
                            default:
                                throw new IllegalStateException();
                        }
                        socketStatus = SocketStatus.READ;

                        if (HandlerStatus.EXEC.equals(status)) {
                            try {
                                Object result = callMethod.invoke(server, methodParams);
                                // cast the result to string
                                String resultValue = null;
                                if (result.getClass().equals(Integer.class)) {
                                    resultValue = String.valueOf((int) result);
                                }
                                if (result.getClass().equals(String.class)) {
                                    resultValue = (String) result;
                                }
                                // add line break for nicer client output
                                resultValue += "\n";
                                // write result
                                ByteBuffer outBuffer = ByteBuffer.wrap(resultValue.getBytes(charset));
                                socketChannel.write(outBuffer);
                            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                                socketStatus = SocketStatus.READ;
                                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                            } finally {
                                callMethod = null;
                                byteBuffer.position(byteBuffer.limit());
                                paramBuffer = new StringBuffer(1024);
                                methodParams = ArrayUtils.EMPTY_OBJECT_ARRAY;
                                methodParamsTypes = null;
                                status = HandlerStatus.METHOD;
                            }
                        }
                    }
                } while (byteBuffer.hasRemaining());
                byteBuffer.clear(); // prepare for reading
            }
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Reads from byteBuffer char wise.
     *
     * @param buffer
     * @param byteBuffer
     *
     */
    private void read(StringBuffer buffer, ByteBuffer byteBuffer) {
        try {
            CharBuffer charBuffer = charset.newDecoder().decode(byteBuffer);
            while(charBuffer.hasRemaining() && SocketStatus.READ.equals(socketStatus)) {
                char c = charBuffer.get();
                if (c != DELIMITER) {
                    buffer.append(c);
                } else {
                    socketStatus = SocketStatus.READ_DONE;
                }
            }
        } catch (CharacterCodingException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
