package it.polimi.ingsw.rete;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Client extends UnicastRemoteObject implements ClientInt{
    private String nickname;
    private static String serverIP;
    static int PORT=8080;

    protected Client() throws RemoteException {
        super();
    }


    public static void main(String[] args) {
        try {
            Client client=new Client();
            Scanner IP=new Scanner(System.in);
            System.out.println("Input IP-Address: ");
            serverIP=IP.nextLine();
            String name="Sagrada server";
            Registry registry= LocateRegistry.getRegistry(serverIP,PORT);
            ServerInt stub= (ServerInt) registry.lookup(name);
            stub.addObserver(client);
        } catch (Exception e) {
            System.err.println("Client exception:   "+ e.toString());
            e.printStackTrace();
        }
    }


    public void update(String msg) throws RemoteException {
        System.out.println("Update from server: "+msg);
    }

    public String setupconnection() throws RemoteException {
        Scanner in=new Scanner(System.in);
        System.out.println("Input your nickname:");
        nickname=in.nextLine();
        return nickname;
    }
}
