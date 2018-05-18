package it.polimi.ingsw.rete;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInt extends Remote {
    public void update(String msg) throws RemoteException;
    public String setupconnection() throws RemoteException;
}
