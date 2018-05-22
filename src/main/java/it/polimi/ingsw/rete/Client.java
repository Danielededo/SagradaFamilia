package it.polimi.ingsw.rete;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

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
            if(!stub.addObserver(client)){
                System.err.println("You have been disconnected");
                registry=null;
                stub=null;
                client=null;
                System.exit(-1);
            }
        } catch (Exception e) {
            System.err.println("Client exception:   "+ e.toString());
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public String getNickname()throws RemoteException {
        return nickname;
    }

    public String getServerIp()throws RemoteException {
        return serverIP;
    }

    public void update(String msg) throws RemoteException {
        System.out.println("Server -> "+msg);
    }

    public String setupPlayer()throws RemoteException{
        final int tim=11;
        System.out.println("Press something to confirm your presence ");
        Scanner in=new Scanner(System.in);
        String b="";
        final String finalB = b;
        TimerTask task = new TimerTask(){
            public void run()
            {
                if( finalB.equals(""))
                {
                    System.out.println( "You input nothing. Exit..." );
                    System.exit(-1);
                }
            }
        };
        Timer timer= new Timer();
        timer.schedule(task,tim*1000);
        b = in.nextLine();
        timer.cancel();
        return b;
    }

    public String setupgame() throws RemoteException{
        Scanner in=new Scanner(System.in);
        System.out.println("Choose your schemecard ");
        String a=in.nextLine();
        return a;
    }
    public String setupconnection() throws RemoteException {
        Scanner in=new Scanner(System.in);
        System.out.println("Input your nickname:");
        nickname=in.nextLine();
        return nickname;
    }

}
