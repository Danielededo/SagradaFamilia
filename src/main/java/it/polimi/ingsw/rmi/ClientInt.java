package it.polimi.ingsw.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInt extends Remote {
    public void update(String msg) throws RemoteException;
    public String setupconnection() throws RemoteException;
    public String getNickname() throws RemoteException;
    public String getServerIp() throws RemoteException;
    public int selection_int() throws RemoteException;
    public void exit() throws RemoteException;
    public void verifyconnection()throws RemoteException;
    public String getPassword() throws RemoteException;
    public boolean isNickerr() throws RemoteException;
    public void setNickerr(boolean nickerr) throws RemoteException;
}
