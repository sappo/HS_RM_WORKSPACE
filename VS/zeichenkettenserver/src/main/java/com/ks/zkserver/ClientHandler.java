package com.ks.zkserver;

import com.ks.zkinterface.ByteBufferUtil;
import com.ks.zkserver.util.HandlerStatus;
import com.ks.zkinterface.ReflectionUtils;
import com.ks.zkinterface.ServerMethod;
import com.ks.zkinterface.ZKServer;
import com.ks.zkinterface.ZkService;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Kevin Sapper (2012)
 */
public class ClientHandler implements Runnable {

    private final Charset charset;

    private final char DELIMITER;

    private Socket client;

    private ZKServer server;

    private HandlerStatus status;

    private Method callMethod;

    private Class<?>[] methodParamsTypes;

    private Object[] methodParams;

    public ClientHandler(Socket client, ZKServer server) {
        this.client = client;
        this.server = server;
        this.status = HandlerStatus.METHOD;
        this.methodParams = ArrayUtils.EMPTY_OBJECT_ARRAY;
        this.charset = ZkService.charset;
        this.DELIMITER = ZkService.DELIMITER;
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
                    if (ByteBufferUtil.readChars(paramBuffer, byteBuffer, DELIMITER, charset)) {
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
                                resultValue += DELIMITER;
                                // write result
                                ByteBuffer outBuffer = ByteBuffer.wrap(resultValue.getBytes(charset));
                                socketChannel.write(outBuffer);
                            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
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

    @Override
    protected void finalize() throws Throwable {
        client.close();
        super.finalize();
    }
}
