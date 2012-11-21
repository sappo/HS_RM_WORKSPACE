package com.ks.zk.zkserver;

import com.ks.zkinterface.ZKServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin Sapper (2012)
 */
public class ZKServerStub implements Runnable {

    private ZKServer server;

    public ZKServerStub(ZKServer server) {
        this.server = server;
    }

    @Override
    public void run() {
        try {
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(8080));
            System.out.println("Server socket listing at port 8080");
            while (true) {
                // create a new server socket and bind it to port
                SocketChannel client = serverChannel.accept();
                ClientHandler handler = new ClientHandler(client.socket(), server);
                new Thread(handler).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ZKServerStub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
