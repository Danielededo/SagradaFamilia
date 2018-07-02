package it.polimi.ingsw.client;

import java.rmi.RemoteException;

public class Client_starter {

    public static void main(String[] args) throws RemoteException {
        Client client=new Client();
        client.startclient(args);
    }
}
