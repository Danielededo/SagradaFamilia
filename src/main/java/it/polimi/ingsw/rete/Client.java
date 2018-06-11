package it.polimi.ingsw.rete;

import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Client extends UnicastRemoteObject implements ClientInt{
    private String nickname;
    private static String serverIP;
    static int PORT;
    private static String password;

    protected Client() throws RemoteException {
        super();
    }

    public String getPassword() throws RemoteException{
        return password;
    }

    public static void main(String[] args) {
        try {
            args[0]=args[0].replaceAll("-","");
            args[1]=args[1].replaceAll("-","");
            PORT= Integer.parseInt(args[0]);
            serverIP=args[1];
            Client client=new Client();
            String name="Sagrada server";
            Registry registry= LocateRegistry.getRegistry(serverIP,PORT);
            ServerInt stub= (ServerInt) registry.lookup(name);
            if(!stub.addObserver(client)){
                System.err.println("Sei stato disconnesso");
                registry=null;
                stub=null;
                client=null;
                System.exit(-1);
            }
        } catch (ConnectException e){
            System.err.println("Il server non è connesso");
            System.exit(-1);
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
        if (!msg.equals("disconnettiti"))
            System.out.println("Server -> "+msg);
        else exit();
    }

    public String setupPlayer()throws RemoteException{
        final int tim=11;
        System.out.println("Digita qualcosa per confermare la tua presenza ");
        Scanner in=new Scanner(System.in);
        String b="";
        final String finalB = b;
        TimerTask task = new TimerTask(){
            @Override
            public void run()
            {
                if( finalB.equals(""))
                {
                    System.out.println( "Non hai risposto. Uscita..." );
                    System.exit(-1);
                    this.cancel();
                }
            }
        };
        Timer timer= new Timer();
        timer.schedule(task,tim*1000);
        b = in.nextLine();
        task.cancel();
        return b;
    }

    public String setupgame() throws RemoteException{
        Scanner in=new Scanner(System.in);
        System.out.println("Scegli la tua carta schema ");
        String a=in.nextLine();
        return a;
    }

    public String setupconnection() throws RemoteException {
        Scanner in=new Scanner(System.in);
        System.out.println("Inserisci il tuo nickname:");
        nickname=in.nextLine();
        Scanner in1=new Scanner(System.in);
        System.out.println("Password:");
        password=in1.nextLine();
        return nickname;
    }

    public int selection_int() throws RemoteException {
        boolean iscorrect=true;
        Scanner s=new Scanner(System.in);
        do {
            try {
                System.out.print("> ");
            }catch (InputMismatchException e) {
                iscorrect=false;
            }
        } while (!iscorrect);
        return s.nextInt();
    }

    public boolean verifyconnection()throws RemoteException{
        return true;
    }

    public void exit()throws RemoteException{
        System.exit(0);
    }
}
