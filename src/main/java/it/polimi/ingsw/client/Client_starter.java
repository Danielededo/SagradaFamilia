package it.polimi.ingsw.client;

import java.rmi.RemoteException;

public class Client_starter {

    public static void main(String[] args) throws RemoteException {
        try {
            args[0]=args[0].replaceAll("-","");
            args[1]=args[1].replaceAll("-","");
            Client client=new Client(args[0],args[1]);
            client.startclient(args);
        }catch (ArrayIndexOutOfBoundsException e){
            System.err.println("Non è stata inserita nessuna porta o indirizzo ip");
            System.exit(-1);
        }
    }
}
