package com.ks.zkserver;

import com.ks.zkinterface.ZKServer;
import java.io.IOException;
import java.net.InetSocketAddress;
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
    private int port;

    public ZKServerStub(ZKServer server, int port) {
        this.server = server;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(port));
            System.out.println("Server socket listing at port "+ port);
            while (true) {
                // create a new server socket and bind it to port
                SocketChannel client = serverChannel.accept();
                System.out.println("Client " + client.getRemoteAddress().toString() + " connected!");
                ClientHandler handler = new ClientHandler(client.socket(), server);
                new Thread(handler).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ZKServerStub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
