package com.ks.rmizkserver;

import com.ks.rmizkinterface.Zeichenkette;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Kevin Sapper (2012)
 */
public class ZeichenketteImpl extends UnicastRemoteObject implements Zeichenkette {

    private String inhalt;

    public ZeichenketteImpl() throws RemoteException {
    }

    @Override
    public String init(String inhalt) throws RemoteException, IllegalArgumentException {
        if (StringUtils.isEmpty(inhalt)) {
            throw new IllegalArgumentException();
        }
        this.inhalt = inhalt;
        return this.inhalt;
    }

    @Override
    public String zeichenweiseVerdoppeln(int anzahl) throws RemoteException, IllegalArgumentException {
        if (anzahl <= 0) {
            throw new IllegalArgumentException();
        }
        StringBuilder sb = new StringBuilder(inhalt.length() * anzahl);
        for (int i = 0; i < inhalt.length(); i++) {
            for (int j = 0; j < anzahl; j++) {
                sb.append(inhalt.charAt(i));
            }
        }
        return sb.toString();
    }

    @Override
    public String verdoppeln(int anzahl) throws RemoteException, IllegalArgumentException {
        if (anzahl <= 0) {
            throw new IllegalArgumentException();
        }
        return StringUtils.repeat(inhalt, anzahl);
    }

    @Override
    public String spiegeln() throws RemoteException {
        return StringUtils.reverse(inhalt);
    }

    @Override
    public int laenge() throws RemoteException {
        return StringUtils.length(inhalt);
    }

    @Override
    public String gibInhalt() throws RemoteException {
        return inhalt;
    }
}
