package it.polimi.ingsw.client;

import it.polimi.ingsw.rmi.ClientInt;
import it.polimi.ingsw.rmi.ServerInt;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;
import java.util.TimerTask;

public class ClientGui extends UnicastRemoteObject implements ClientInt {
    private String nickname;
    private static String serverIP;
    static int PORT;
    private ServerInt stub;
    private String password;
    private boolean nickerr = false;


    private StringProperty fromServer = new SimpleStringProperty("");
    private IntegerProperty toServer = new SimpleIntegerProperty();







    public ClientGui(String[] args) throws RemoteException {
        super();

        try {
            args[0]=args[0].replaceAll("-","");
            args[1]=args[1].replaceAll("-","");
            PORT= Integer.parseInt(args[0]);
            serverIP=args[1];
            String name="Sagrada server";
            Registry registry= LocateRegistry.getRegistry(serverIP,PORT);
            stub= (ServerInt) registry.lookup(name);
            /*if(!stub.addObserver(this)){
                System.err.println("You have been disconnected");
                registry=null;
                stub=null;
                System.exit(-1);
            }*/
            Timer timer=new Timer();
            TimerTask task=new TimerTask() {
                @Override
                public void run() {
                    verifyconnection();
                }
            };
            timer.scheduleAtFixedRate(task,0,1000);
        } catch (Exception e) {
            System.err.println("Client exception:   "+ e.toString());
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public ServerInt getStub() {
        return stub;
    }

    public String getNickname(){
        return nickname;
    }

    public String getServerIp()throws RemoteException {
        return serverIP;
    }

    public void update(String msg) throws RemoteException {
        if(!msg.equals("disconnettiti"))
            fromServer.setValue(msg);
        else
            exit();
    }


    public String setupconnection() throws RemoteException {

        return nickname;
    }



    public int selection_int() throws RemoteException {
        return toServer.getValue();
    }



    public boolean isNickerr() throws RemoteException{
        return nickerr;
    }

    public void setNickerr(boolean nickerr) throws RemoteException{
        this.nickerr = nickerr;
    }

    public void verifyconnection(){
        try {
            stub.ping();
        } catch (RemoteException e) {
            System.err.println("Server offline");
            System.exit(-1);
        }
    }

    @Override
    public String getPassword() throws RemoteException {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public void exit()throws RemoteException{
        System.exit(0);
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public int getToServer() {
        return toServer.get();
    }

    public IntegerProperty toServerProperty() {
        return toServer;
    }

    public void setToServer(int toServer) {
        this.toServer.set(toServer);
    }

    public String getFromServer() {
        return fromServer.get();
    }

    public StringProperty fromServerProperty() {
        return fromServer;
    }

    public void setFromServer(String fromServer) {
        this.fromServer.set(fromServer);
    }
}
