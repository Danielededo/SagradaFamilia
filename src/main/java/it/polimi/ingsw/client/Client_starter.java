package it.polimi.ingsw.client;

import it.polimi.ingsw.utils.Constants;

import java.rmi.RemoteException;

public class Client_starter {

    public static void main(String[] args) throws RemoteException {
        Client client = null;
        String port = null,ip = null;
        int i=0;
        try {
            while(i<args.length) {
                if (args[i].equals("-p") && i == 1) {
                    port = args[i + 1];
                    ip = args[0];
                } else if (args[i].equals("-p") && i == 0) {
                    port = args[i + 1];
                    ip = args[i + 2];
                }
                i++;
            }
            if (args.length==1){
                client=new Client(Constants.config.get(0),args[0]);
            }else client=new Client(port,ip);
            client.startclient();
        }catch (ArrayIndexOutOfBoundsException|NullPointerException e){
            System.err.println("Non è stata inserita nessuna porta o indirizzo ip");
            System.out.println("usage: LM_15_client.jar IP_ADDRESS [-p PORT_NUMBER]");
            System.exit(-1);
        }
    }
}
