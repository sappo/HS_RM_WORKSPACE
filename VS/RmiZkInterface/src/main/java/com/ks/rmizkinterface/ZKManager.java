package com.ks.rmizkinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Kevin Sapper (2012)
 */
public interface ZKManager extends Remote {

    Zeichenkette erzeugeZeichenkette(String id, String inhalt) throws RemoteException, IllegalArgumentException;

    void loescheZeichenkette(String id) throws RemoteException;

    Zeichenkette gibZeichenkette(String id) throws RemoteException;
    
    void exportZeichenkette(String id) throws RemoteException;
}
