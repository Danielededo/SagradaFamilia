package it.polimi.ingsw.client;

import java.rmi.RemoteException;

public class Client_starter {

    public static void main(String[] args) throws RemoteException {
        try {
            Client client = null;
            if (args[0].equals("-p")&&args[2].equals("-ip")){client=new Client(args[1],args[3]);}
            else if (args[0].equals("-ip")&&args[2].equals("-p")){client=new Client(args[3],args[1]);}
            client.startclient();
        }catch (NullPointerException|ArrayIndexOutOfBoundsException e){
            System.err.println("Non è stata inserita nessuna porta o indirizzo ip");
            System.out.println("usage: LM_15_client.jar -p PORT_NUMBER -ip IP_ADDRESS");
            System.exit(-1);
        }
    }
}
