package it.polimi.ingsw.gui;

import it.polimi.ingsw.client.ClientGui;
import it.polimi.ingsw.gui.components.BoxUnsure;
import it.polimi.ingsw.gui.components.mainboard.Adversary;
import it.polimi.ingsw.gui.scenarios.*;
import it.polimi.ingsw.rmi.ClientInt;
import it.polimi.ingsw.utils.Constants;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.ArrayList;


public class GUI extends Application implements ClientInt {
    private SimpleStringProperty stolen = new SimpleStringProperty();
    private IntegerProperty guisays = new SimpleIntegerProperty();

    private static Registry registry;
    private static ClientGui clientGui;

    private Scene tavolo;
    private SchermataLog loggin = new SchermataLog();
    private WaitingR wr = new WaitingR();
    private MainBoard main = new MainBoard();
    private Disconnected dis = new Disconnected();
    private Ranking rank = new Ranking();


    private BoxUnsure uscita = new BoxUnsure();

    private ArrayList<String> players = new ArrayList<>();



    public static void main(String[] args) throws RemoteException {
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
                    clientGui=new ClientGui(Constants.config.get(0),args[0]);
                }else clientGui=new ClientGui(port,ip);
            }catch (ArrayIndexOutOfBoundsException|NullPointerException e){
                System.err.println("Non è stata inserita nessuna porta o indirizzo ip");
                System.out.println("usage: LM_15_client.jar IP_ADDRESS [-p PORT_NUMBER]");
                System.exit(-1);
            }
            launch(args);
    }

    public void start(Stage stage) {
        stage.setOnCloseRequest(event -> System.exit(-1));

        stolen.addListener((observable, oldValue, newValue) -> talkingToServer(oldValue,newValue,stage));
        stolen.addListener(((observable, oldValue, newValue) -> main.setting(oldValue, newValue, guisays)));
        stolen.addListener(((observable, oldValue, newValue) -> main.duringTurn(oldValue, newValue, guisays)));
        stolen.addListener(((observable, oldValue, newValue) -> main.placeThatDie(oldValue, newValue, guisays)));
        stolen.addListener(((observable, oldValue, newValue) -> main.tooling(oldValue, newValue, guisays)));
        stolen.addListener(((observable, oldValue, newValue) -> wr.wrlistens(oldValue, newValue)));

        stolen.addListener(((observable, oldValue, newValue) -> endGame(oldValue, newValue, stage)));


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
        stage.setScene(new Scene(loggin,800,600));
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



    public void talkingToServer(String oldie, String newie, Stage stage) {
        if (oldie.equals("welcome")) {
            Platform.runLater(() -> {
                wr.getCurrent().setText(newie);
                stage.setScene(new Scene(wr));
                stage.show();
            });
        } else if (newie.equals(Constants.RECONNECTED)){
            Platform.runLater(() -> {
                stage.setScene(new Scene(main));
                stage.show();
            });

        }else if (oldie.equals("connesso")) {
            Platform.runLater(() -> {
                Label gioc = new Label(newie);
                wr.getPlayers().getChildren().add(gioc);
            });
        } else if (oldie.equals("Left")) {
            Platform.runLater(() -> {
                Label gioc = new Label(newie);
                wr.getPlayers().getChildren().add(gioc);
                wr.getSoli().setText("");
            });
        } else if (oldie.equals("Timer stop")) {
            if (newie.equals("Solo")) {
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
    }

    public void endGame(String oldie, String newie, Stage stage){
        if(oldie.equals(Constants.END_GAME)){
            Platform.runLater(() -> {
                JSONArray ranking = new JSONArray(newie);
                int i = 0;
                while (i < ranking.length()){
                    JSONObject provv = ranking.getJSONObject(i);
                    rank.add(new Label(provv.getString("player")), 0, i + 1);
                    rank.add(new Label(provv.getString("score")), 1, i + 1);
                    i++;
                }
                Label winner = new Label(ranking.getJSONObject(0).getString("player") + " HA VINTO");
                winner.setTextFill(Paint.valueOf("#ccffff"));
                winner.setStyle("-fx-font-size: 30");
                rank.add(winner,0,5, 4,1);
                stage.setScene(new Scene(rank));
                stage.show();
            });
        }else if(oldie.equals(Constants.DISCONNECTED)){
            Platform.runLater(()->{
                for(Adversary a: main.getAdv()){
                    if(a.getName().equals(newie))
                        a.getLabel().setText("disconnesso");
                }
            });
        }else if(newie.equals(Constants.WINNER)){
            Platform.runLater(()->{
                Label lab= new Label();
                lab.setText(Constants.WINNER);
                lab.setStyle("-fx-font-size: 30");
                lab.setTextFill(Paint.valueOf("#6600cc"));
                StackPane stackPane= new StackPane();
                stackPane.setStyle("-fx-background-color: #cce6ff");
                stackPane.getChildren().add(lab);
                stackPane.setAlignment(Pos.CENTER);
                stage.setScene(new Scene(stackPane, 1000,600));
                stage.show();
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

    @Override
    public void connected() throws RemoteException {

    }
}
