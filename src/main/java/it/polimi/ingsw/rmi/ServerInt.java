package it.polimi.ingsw.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInt extends Remote {
    public boolean addObserver(ClientInt o) throws RemoteException;
    public void ping() throws RemoteException;
}
