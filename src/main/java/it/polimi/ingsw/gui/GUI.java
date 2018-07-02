package it.polimi.ingsw.gui;

import it.polimi.ingsw.client.ClientGui;
import it.polimi.ingsw.gui.components.BoxUnsure;
import it.polimi.ingsw.gui.components.Building;
import it.polimi.ingsw.gui.scenarios.Disconnected;
import it.polimi.ingsw.gui.scenarios.MainBoard;
import it.polimi.ingsw.gui.scenarios.SchermataLog;
import it.polimi.ingsw.gui.scenarios.WaitingR;
import it.polimi.ingsw.rmi.ClientInt;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.ArrayList;


public class GUI extends Application implements ClientInt {
    //private String nickname;
    //private static String serverIP;
    //static int PORT;
    private SimpleStringProperty stolen = new SimpleStringProperty();
    private IntegerProperty guisays = new SimpleIntegerProperty();

    private static Registry registry;
    private static ClientGui clientGui;

    private Scene tavolo;
    private SchermataLog loggin = new SchermataLog();
    private WaitingR wr = new WaitingR();
    private MainBoard main = new MainBoard();
    private Disconnected dis = new Disconnected();
    private Building necessary = new Building();


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
        stage.setOnCloseRequest(event -> System.exit(-1));

        stolen.addListener((observable, oldValue, newValue) -> talkingToServer(oldValue,newValue));
        stolen.addListener(((observable, oldValue, newValue) -> {
            if(oldValue.equals("Timer")) {
                Platform.runLater(() -> wr.getOther().setText(newValue));
            }else if(oldValue.equals("Timer stop")) {
                if (newValue.equals("Solo")) {
                    Platform.runLater(() -> {
                        wr.getSoli().setText("Attendi che uno o più giocatori partecipino alla partita");
                        wr.getOther().setText("");
                });
            } else {
                Platform.runLater(() -> {
                    stage.setScene(new Scene(main));
                    stage.show();
                });
            }
        }
    }));

        stolen.addListener(((observable, oldValue, newValue) -> main.setting(oldValue, newValue, guisays)));
        stolen.addListener(((observable, oldValue, newValue) -> main.duringTurn(oldValue, newValue, guisays)));


        loggin.getClick().onMouseClickedProperty().set( e -> {
            try {
                if(loggin.getNickInput().getText().equals("") || loggin.getPassInput().getText().equals("")){
                    loggin.getMex().setText("Per favore reinserisci nome e password, i campi non devono essere vuoti.");
                    loggin.getPassInput().clear();
                    loggin.getNickInput().clear();

                }else if(loggin.getNickInput().getText().contains(" ") || loggin.getPassInput().getText().contains(" ")){
                    loggin.getMex().setText("Nome e password non possono contenere spazi, per favore reinseriscili.");
                    loggin.getPassInput().clear();
                    loggin.getNickInput().clear();
                }else {
                    clientGui.setNickname(loggin.getNickInput().getText());
                    clientGui.setPassword(loggin.getPassInput().getText());


                    if (!clientGui.getStub().addObserver(clientGui)) {
                        if (clientGui.isNickerr() && stolen.getValue().equals("Questo nickname è già stato usato da un altro giocatore")) {
                            loggin.getMex().setText("Questo nickname è già stato usato da un altro giocatore" + "\rPer favore inseriscine un altro.");
                            loggin.getNickInput().setText("");
                            loggin.getPassInput().setText("");
                        } else {
                            tavolo = new Scene(dis);
                            stage.setScene(tavolo);
                            stage.show();
                        }
                    } else {
                        stage.setScene(new Scene(wr));
                        stage.show();
                    }
                }
            }catch (RemoteException ex) {

                tavolo = new Scene(dis);
                tavolo.setFill(Paint.valueOf("#000000"));
                stage.setScene(tavolo);
                stage.show();
                registry = null;
            }
        });
        stage.setScene(new Scene(loggin));
        stage.show();
    }

    public GUI(){
        super();
        stolen.bind(clientGui.fromServerProperty());
        clientGui.toServerProperty().bind(guisays);
    }

    /*public void closeProgram(Stage stage){
        Boolean esito = uscita.display("Uscita", "Sei sicuro di voler uscire?");
        if(esito){ stage.close(); }
    }*/

    @Override
    public void update(String msg) throws RemoteException {
    }

    @Override
    public String setupconnection() throws RemoteException {
        return null;
    }



    public void talkingToServer(String oldie, String newie){
        if(oldie.equals("welcome")){
            wr.getCurrent().setText(newie);
        }else if(oldie.equals("connesso")){
            Platform.runLater(() -> {
                Label gioc = new Label(newie);
                wr.getPlayers().getChildren().add(gioc);
            });
        }


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
    public void verifyconnection() throws RemoteException {

    }

    @Override
    public String getPassword() throws RemoteException {
        return null;
    }

    @Override
    public boolean isNickerr() throws RemoteException {
        return false;
    }

    @Override
    public void setNickerr(boolean nickerr) throws RemoteException {

    }

    public String getStolen() {
        return stolen.get();
    }

    public SimpleStringProperty stolenProperty() {
        return stolen;
    }

    public void setStolen(String stolen) {
        this.stolen.set(stolen);
    }
}
