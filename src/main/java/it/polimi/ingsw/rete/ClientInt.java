package it.polimi.ingsw.rete;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInt extends Remote {
    public String setupPlayer() throws RemoteException;
    public void update(String msg) throws RemoteException;
    public String setupconnection() throws RemoteException;
    public String getNickname() throws RemoteException;
    public String getServerIp() throws RemoteException;
    public String setupgame() throws RemoteException;
    public int selection_int() throws RemoteException;
    public void exit() throws RemoteException;
    public boolean verifyconnection()throws RemoteException;
}
