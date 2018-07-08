package it.polimi.ingsw.client;

import it.polimi.ingsw.rmi.ClientInt;
import it.polimi.ingsw.rmi.ServerInt;

import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;
import java.rmi.UnmarshalException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Client extends UnicastRemoteObject implements ClientInt {
    private String nickname;
    private static String serverIP;
    static int PORT;
    private static String password;
    private boolean nickerr = false;
    private ServerInt stub;


    protected Client(String PORT,String serverIP) throws RemoteException {
        super();
        this.serverIP=serverIP;
        try {
            this.PORT= Integer.parseInt(PORT);
        } catch (NumberFormatException e) {
            System.err.println("Porta inserita non valida");
            System.out.println("usage: LM_15_client.jar IP_ADDRESS [-p PORT_NUMBER]");
            System.exit(-1);
        }
    }

    public String getPassword() throws RemoteException{
        return password;
    }

    /**
     * This method is used to connect client to server via RMI
     */
    public void startclient() {
        try {
            String name="Sagrada server";
            Registry registry= LocateRegistry.getRegistry(serverIP,PORT);
            stub= (ServerInt) registry.lookup(name);
            if(!stub.addObserver(this)){
                System.err.println("Sei stato disconnesso");
                registry=null;
                stub=null;
                System.exit(-1);
            }
            Timer timer=new Timer();
            TimerTask task=new TimerTask() {
                @Override
                public void run() {
                    verifyconnection();
                }
            };
            timer.scheduleAtFixedRate(task,0,1000);
        } catch (ConnectException e){
            System.err.println("Nessun server connesso a questo indirizzo ip su questa porta");
            System.exit(-1);
        } catch (UnmarshalException ignored){
        } catch (UnknownHostException e){
            System.err.println("Indirizzo ip non valido");
            System.exit(-1);
        } catch (Exception e) {
            System.exit(-1);
        }
    }

    public String getNickname()throws RemoteException {
        return nickname;
    }

    public String getServerIp()throws RemoteException {
        return serverIP;
    }

    /**
     * Update client with message sent by server
     * @param msg is the message that server send
     * @throws RemoteException
     */
    public void update(String msg) throws RemoteException {
        if (!msg.equals("")){
            if (!msg.equals("disconnettiti"))
                System.out.println("Server -> "+msg);
            else exit();
        }
    }

    /**
     * This method is called from server to enter username and password
     * @return username of current client
     * @throws RemoteException
     */
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

    /**
     * This method notify to client if server is off
     */
    public void verifyconnection(){
        try {
            stub.ping();
        } catch (RemoteException e) {
            System.err.println("Server offline");
            System.exit(-1);
        }
    }

    /**
     * Server called this method if something goes wrong and disconnect client
     * @throws RemoteException
     */
    public void exit()throws RemoteException{
        System.exit(0);
    }

    @Override
    public String toString() {
        return "Client{" +
                "nickname='" + nickname + '\'' +
                '}';
    }

    public boolean isNickerr() throws RemoteException{
        return nickerr;
    }

    @Override
    public void setNickerr(boolean nickerr) throws RemoteException{
        this.nickerr = nickerr;
    }

    @Override
    public void connected() throws RemoteException {
    }
}
