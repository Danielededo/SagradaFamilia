package it.polimi.ingsw.rete;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInt extends Remote {

    public void addObserver(ClientInt o) throws RemoteException;
    public void removeObserver(ClientInt o) throws RemoteException;
    public void notifyObserver(String arg) throws RemoteException;
    public void loginconnection(int index) throws RemoteException;
    /*public String sceglidadodariserva();
    public boolean posizionadado();*/

}
