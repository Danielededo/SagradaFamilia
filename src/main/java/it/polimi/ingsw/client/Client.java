package it.polimi.ingsw.client;

import it.polimi.ingsw.rmi.ClientInt;
import it.polimi.ingsw.rmi.ServerInt;

import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client extends UnicastRemoteObject implements ClientInt {
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
        } catch (UnmarshalException e){} catch (Exception e) {
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

    public String setupconnection() throws RemoteException {
        boolean a=true;
        Scanner in = new Scanner(System.in);
        do {
            if(a)
                System.out.println("Inserisci il tuo nickname:");
            else
                System.out.println("Nickname non valido\nReinserisci il tuo nickname:");
            nickname = in.nextLine();
            a=false;
        }while(nickname.equals("") || nickname.contains(" "));
        Scanner in1=new Scanner(System.in);
        do {
            if(!a)
                System.out.println("Inserisci la tua password:");
            else
                System.out.println("Password non valida\nReinserisci la tua password:");
            password=in1.nextLine();
            a=true;
        } while (password.equals("") || password.contains(" "));
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

    @Override
    public String toString() {
        return "Client{" +
                "nickname='" + nickname + '\'' +
                '}';
    }
}