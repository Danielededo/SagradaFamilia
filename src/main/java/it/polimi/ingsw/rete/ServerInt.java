package it.polimi.ingsw.rete;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInt extends Remote {

    public boolean addObserver(ClientInt o) throws RemoteException;
    public void removeObserver(ClientInt o) throws RemoteException;
    public void notifyObserver(String arg) throws RemoteException;
    public void notifyOthers(ClientInt c,String arg) throws RemoteException;
    public boolean loginconnection(ClientInt c) throws RemoteException;
    /*public String sceglidadodariserva();
    public boolean posizionadado();*/

}
