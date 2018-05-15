package it.polimi.ingsw.Rete;

import it.polimi.ingsw.Game.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInt extends Remote {

    public boolean login(String player) throws RemoteException;
    public void logout(String player)throws RemoteException;
    public String show_waitingroom()throws RemoteException;
    public void show_client_connessi() throws RemoteException;
    /*public String sceglidadodariserva();
    public boolean posizionadado();*/

}
