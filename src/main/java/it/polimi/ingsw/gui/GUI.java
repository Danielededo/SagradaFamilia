package it.polimi.ingsw.gui;

import it.polimi.ingsw.client.ClientGui;
import it.polimi.ingsw.gui.components.BoxUnsure;
import it.polimi.ingsw.rmi.ClientInt;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.ArrayList;


public class GUI extends Application implements ClientInt {
    //private String nickname;
    //private static String serverIP;
    //static int PORT;
    private SimpleStringProperty fromServer = new SimpleStringProperty("");
    private static Registry registry;

    private static ClientGui clientGui;
    private static Stage mainone = new Stage();
    private Scene tavolo;
    private VariLayout heythere = new VariLayout();
    private BoxUnsure uscita = new BoxUnsure();

    private ArrayList<String> players = new ArrayList<>();






    public static void main(String[] args){
        try {
            clientGui=new ClientGui(args);
            launch(args);

            /**/

        }catch (Exception e){
            System.err.println("Client exception:   "+ e.toString());
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void start(Stage stage) {
        heythere.getClick().onMouseClickedProperty().set( e -> {
            try {
                clientGui.setNickname(heythere.getNickInput().getText());
                clientGui.setPassword(heythere.getPassInput().getText());

                if(!clientGui.getStub().addObserver(clientGui)){
                    VBox byebye = heythere.disconnesso();
                    tavolo = new Scene(byebye);
                    tavolo.setFill(Paint.valueOf("#000000"));
                    stage.setScene(tavolo);
                    stage.show();

                }
                else {
                    BorderPane waitingr = heythere.waitingRoom(heythere.getScheme());
                    heythere.getCurrent().setText(" Ciao " + clientGui.getNickname() + ", risulti connesso.");

                    tavolo = new Scene(waitingr);

                    stage.setScene(tavolo);
                    stage.setFullScreen(true);
                    stage.show();
                }
            }catch (RemoteException ex){
                VBox byebye = heythere.disconnesso();
                tavolo = new Scene(byebye);
                tavolo.setFill(Paint.valueOf("#000000"));
                stage.setScene(tavolo);
                stage.show();
                registry=null;
            }
        });
        VBox layout = heythere.SchermataLog();
        tavolo = new Scene(layout);
        tavolo.setFill(Paint.valueOf("#000000"));
        stage.setScene(tavolo);
        stage.setFullScreen(true);
        stage.show();
    }

    public GUI(){
        super();
    }

    /*public void closeProgram(Stage stage){
        Boolean esito = uscita.display("Uscita", "Sei sicuro di voler uscire?");
        if(esito){ stage.close(); }
    }*/


    @Override
    public void update(String msg) throws RemoteException {
        fromServer.set(msg);
    }

    @Override
    public String setupconnection() throws RemoteException {
        return null;
    }



    public void talkingToServer(String msg){
        if(msg == "Questo nickname è già stato usato da un altro giocatore"){
            clientGui.setNickname("");
            clientGui.setPassword("");
            heythere.getNickInput().clear();
            heythere.getPassInput().clear();
            heythere.getMex().setText("Questo nickname è già stato usato da un altro giocatore. \nPer favore inseriscine un altro.");
        }
        //if()
    }



    @Override
    public String getNickname() throws RemoteException {
        return null;
    }

    @Override
    public String getServerIp() throws RemoteException {
        return null;
    }


    @Override
    public int selection_int() throws RemoteException {
        return 0;
    }

    @Override
    public void exit() throws RemoteException {

    }

    @Override
    public boolean verifyconnection() throws RemoteException {
        return false;
    }

    @Override
    public String getPassword() throws RemoteException {
        return null;
    }

    public String getFromServer() {
        return fromServer.get();
    }

    public SimpleStringProperty fromServerProperty() {
        return fromServer;
    }

    public void setFromServer(String fromServer) {
        this.fromServer.set(fromServer);
    }
}
