package com.ks.rmizkserver;

import com.ks.rmizkinterface.ZKManager;
import com.ks.rmizkinterface.Zeichenkette;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Kevin Sapper (2012)
 */
public class ZKManagerImpl implements ZKManager {

    private Map<String, Zeichenkette> zkMap;

    public ZKManagerImpl() throws RemoteException {
        super();
        zkMap = new HashMap<>();
    }

    @Override
    public Zeichenkette erzeugeZeichenkette(String id, String inhalt) throws RemoteException, IllegalArgumentException {
        if(StringUtils.isEmpty(id) || StringUtils.isEmpty(inhalt)) {
            throw new IllegalArgumentException("Parameter null");
        }
        ZeichenketteImpl zk = new ZeichenketteImpl();
        zk.init(inhalt);
        zkMap.put(id, zk);
        return zk;
    }

    @Override
    public void loescheZeichenkette(String id) throws RemoteException, IllegalArgumentException {
        if(StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("Parameter null");
        }
        zkMap.remove(id);
        try {
            Registry registry = LocateRegistry.getRegistry(6000);
            registry.unbind(id);
        } catch (NotBoundException | AccessException ex) {
            Logger.getLogger(ZKManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Zeichenkette gibZeichenkette(String id) throws RemoteException, IllegalArgumentException {
        if(StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("Parameter null");
        }
        if (zkMap == null || zkMap.isEmpty()) {
            return null;
        } else {
            return zkMap.get(id);
        }
    }

    @Override
    public void exportZeichenkette(String id) throws RemoteException, IllegalArgumentException {
        if(StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("Parameter null");
        }
        try {
            Registry registry = LocateRegistry.getRegistry(6000);
            ZeichenketteImpl zk = (ZeichenketteImpl) zkMap.get(id);
            registry.rebind(id, zk);
        } catch (AccessException ex) {
            Logger.getLogger(ZKManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
