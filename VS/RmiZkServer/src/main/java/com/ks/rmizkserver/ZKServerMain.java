package com.ks.rmizkserver;

import java.io.Serializable;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin Sapper (2012)
 */
public class ZKServerMain implements Serializable {

    public static void main(String[] args) {
        System.out.println("Server ...");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        try { //special exception handler for registry creation
            String rmiName = "zkManager";
            ZKManagerImpl zKManager = new ZKManagerImpl();

            Remote stub = UnicastRemoteObject.exportObject(zKManager, 6000);

            Registry registry = LocateRegistry.createRegistry(6000);
            registry.rebind(rmiName, stub);

        } catch (Exception ex) {
            Logger.getLogger(ZKServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("... started!");
    }
}