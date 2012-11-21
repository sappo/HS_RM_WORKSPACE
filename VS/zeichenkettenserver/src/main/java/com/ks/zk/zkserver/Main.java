package com.ks.zk.zkserver;

import com.ks.zkinterface.ZKServer;

/**
 *
 * @author Kevin Sapper (2012)
 */
public class Main {

    public static void main(String[] args) {
        ZKServer server = new ZKServerImpl();
        ZKServerStub stub = new ZKServerStub(server);
        new Thread(stub).start();
        System.out.println("Server startet!");
    }
}