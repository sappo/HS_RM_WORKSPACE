package com.ks.rmizkinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Kevin Sapper (2012)
 */
public interface Zeichenkette extends Remote {

    String init(String inhalt) throws RemoteException, IllegalArgumentException;

    String zeichenweiseVerdoppeln(int anzahl) throws RemoteException, IllegalArgumentException;

    String verdoppeln(int anzahl) throws RemoteException, IllegalArgumentException;

    String spiegeln() throws RemoteException;

    int laenge() throws RemoteException;

    String gibInhalt() throws RemoteException;
}
