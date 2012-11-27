package com.ks.zkserver;

import com.ks.zkinterface.ZKServer;

/**
 *
 * @author Kevin Sapper (2012)
 */
public class ZKServerMain {

    public static void main(String[] args) {
        ZKServer server = new ZKServerImpl();
        int port = 8080;
        if (args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException ex) {
            }
        }
        ZKServerStub stub = new ZKServerStub(server, port);
        new Thread(stub).start();
        System.out.println("Server startet!");
    }
}