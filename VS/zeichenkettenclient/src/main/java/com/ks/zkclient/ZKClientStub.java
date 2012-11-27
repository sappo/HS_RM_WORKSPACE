package com.ks.zkclient;

import com.ks.zkinterface.ByteBufferUtil;
import com.ks.zkinterface.ServerMethod;
import com.ks.zkinterface.ZKServer;
import com.ks.zkinterface.ZkService;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin Sapper (2012)
 */
public class ZKClientStub implements ZKServer {

    private SocketChannel socketChannel;

    private final Charset charset;

    private final char DELIMITER;

    public ZKClientStub(InetSocketAddress address) throws IOException {
        this.charset = ZkService.charset;
        this.DELIMITER = ZkService.DELIMITER;
        this.socketChannel = SocketChannel.open(address);
    }

    private String send(StringBuffer command) {
        StringBuffer result = new StringBuffer(1024);
        try {
            // write command
            ByteBuffer commandBuffer = ByteBuffer.wrap(command.toString().getBytes(charset));
            socketChannel.write(commandBuffer);
            // read result
            ByteBuffer resultBuffer = ByteBuffer.allocate(1024);
            do {
                resultBuffer.clear();
                socketChannel.read(resultBuffer);
                resultBuffer.flip();
            } while (!ByteBufferUtil.readChars(result, resultBuffer, DELIMITER, charset));
        } catch (IOException ex) {
            Logger.getLogger(ZKClientStub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result.toString();
    }

    @Override
    @ServerMethod
    public String verdoppeln(String text, int quantity) {
        StringBuffer command = new StringBuffer(1024);
        command.append(getMethodName()).append(DELIMITER)
                .append(text).append(DELIMITER)
                .append(quantity).append(DELIMITER);
        return send(command);
    }

    @Override
    @ServerMethod
    public String zeichenweisesVerdoppeln(String text, int quantity) {
        StringBuffer command = new StringBuffer(1024);
        command.append(getMethodName()).append(DELIMITER)
                .append(text).append(DELIMITER)
                .append(quantity).append(DELIMITER);
        return send(command);
    }

    @Override
    @ServerMethod
    public String spiegeln(String text) {
        StringBuffer command = new StringBuffer(1024);
        command.append(getMethodName()).append(DELIMITER)
                .append(text).append(DELIMITER);
        return send(command);
    }

    @Override
    @ServerMethod
    public int laenge(String text) {
        StringBuffer command = new StringBuffer(1024);
        command.append(getMethodName()).append(DELIMITER)
                .append(text).append(DELIMITER);
        return Integer.valueOf(send(command));
    }

    public String getMethodName() {
        try {
            StackTraceElement element = Thread.currentThread().getStackTrace()[2];
            return element.getMethodName();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
