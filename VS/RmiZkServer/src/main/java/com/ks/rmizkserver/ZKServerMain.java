package com.ks.rmizkserver;

import com.ks.rmizkinterface.ZKManager;
import java.io.Serializable;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
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
            LocateRegistry.createRegistry(6000);
            ZKManager zk = new ZKManagerImpl();
        } catch (Exception ex) {
            Logger.getLogger(ZKServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("... started!");
    }
}