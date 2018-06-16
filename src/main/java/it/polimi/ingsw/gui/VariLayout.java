package it.polimi.ingsw.gui;

import it.polimi.ingsw.gui.components.Building;
import it.polimi.ingsw.gui.components.Component;
import it.polimi.ingsw.server.utils.Constants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Random;


public class VariLayout {
    private Building necessity = new Building();

    private Button click = new Button("Log in");
    //private StringProperty nick = new SimpleStringProperty("");
    private TextField nickInput = new TextField();
    private PasswordField passInput = new PasswordField();
    private Label mex = new Label("");


    private Button closeDef = new Button("Exit");


    private ArrayList<Component> scheme = necessity.buildingHolders(Constants.IMG_PRINC + Constants.IMG_SCHE);
    private Label current = new Label("");
    private Label g2 = new Label("DISCONNESSO");
    private Label g3 = new Label("DISCONNESSO");
    private Label g4 = new Label("DISCONNESSO");




    public VBox SchermataLog() {

        VBox layoutlog = new VBox();
        layoutlog.setAlignment(Pos.CENTER);
        layoutlog.setVisible(true);
        layoutlog.setStyle("-fx-background-color: black;");

        Image logo = new Image("/images/logoscritta.png");
        ImageView logoview = new ImageView(logo);

        GridPane griglialog = new GridPane();
        griglialog.setVgap(8);
        griglialog.setHgap(10);
        griglialog.setPadding(new Insets(30, 800, 30, 775));
        griglialog.setStyle("-fx-background-color: " + Constants.BLACK + ";");

        Label nickLabel = new Label("Nickname:");
        GridPane.setConstraints(nickLabel, 0, 0);
        GridPane.setConstraints(nickInput, 1, 0);

        Label passLabel = new Label("Password:");
        GridPane.setConstraints(passLabel, 0, 1);
        GridPane.setConstraints(passInput, 1, 1);


        nickLabel.setVisible(true);
        nickLabel.setTextFill(Color.WHITE);

        passInput.setPromptText("password");

        passLabel.setVisible(true);
        passLabel.setTextFill(Color.WHITE);


        GridPane.setConstraints(click, 1, 2);
        griglialog.getChildren().addAll(nickLabel, nickInput, passLabel, passInput, click);

        layoutlog.getChildren().addAll(logoview, griglialog, mex);
        return layoutlog;
    }

    public VBox disconnesso(){
        Label mexa = new Label("SEI STATO DISCONNESSO. \nBYE.");
        mexa.setStyle("-fx-font-size: 50");
        VBox root = new VBox();
        root.setStyle("-fx-background-color: black");
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(600,600,600,600));
        root.getChildren().addAll(mexa, closeDef);
        return root;
    }


    public BorderPane waitingRoom(ArrayList<Component> scheme){
        HBox root = new HBox();

        Random numero = new Random();
        int i = numero.nextInt(4);

        VBox gioc1 = new VBox();
        gioc1.setAlignment(Pos.CENTER);
        ImageView vetcurr = scheme.get(i);
        scheme.remove(i);
        current.setTextFill(Paint.valueOf("#ffffff"));
        gioc1.getChildren().addAll(vetcurr, current);

        /*i = numero.nextInt(3);
        VBox gioc2 = new VBox();
        gioc2.setAlignment(Pos.CENTER);
        ImageView vetg2 = scheme.get(i);
        scheme.remove(i);
        g2.setTextFill(Paint.valueOf("#ffffff"));
        gioc2.getChildren().addAll(vetg2, g2);

        i = numero.nextInt(2);
        VBox gioc3 = new VBox();
        gioc3.setAlignment(Pos.CENTER);
        ImageView vetg3 = scheme.get(i);
        scheme.remove(i);
        g3.setTextFill(Paint.valueOf("#ffffff"));
        gioc3.getChildren().addAll(vetg3, g3);

        i = 0;
        VBox gioc4 = new VBox();
        gioc4.setAlignment(Pos.CENTER);
        ImageView vetg4 = scheme.get(i);
        scheme.remove(i);
        g4.setTextFill(Paint.valueOf("#ffffff"));
        gioc4.getChildren().addAll(vetg4, g4);

        root.getChildren().addAll(gioc1, gioc2, gioc3, gioc4);*/
        root.getChildren().add(gioc1);
        root.setSpacing(100);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: black;");


        BorderPane main = new BorderPane();
        main.setCenter(root);

        mex.setText("BENVENUTO");
        mex.setTextFill(Paint.valueOf("#ffffff"));

        main.setBottom(mex);

        return main;
    }

    public BorderPane mainBoard(){

        BorderPane one = new BorderPane();
        BorderPane two = new BorderPane();
        BorderPane three = new BorderPane();


        BorderPane personal = new BorderPane();
        HBox cards = new HBox();
        HBox adversus = new HBox();
        VBox half = new VBox();
        half.getChildren().addAll(cards, adversus);
        three.setCenter(half);
        two.setCenter(three);
        two.setRight(personal);
        one.setCenter(two);
        return one;
    }



    public ArrayList<Component> getScheme() {
        return scheme;
    }



    public Button getClick() {
        return click;
    }

    public void setClick(Button click) {
        this.click = click;
    }

    public Button getCloseDef() {
        return closeDef;
    }
    public Label getMex() {
        return mex;
    }

    public void setMexText(String mexText) {
        this.mex.setText(mexText);
    }

    public PasswordField getPassInput() {
        return passInput;
    }

    public TextField getNickInput() {
        return nickInput;
    }

    public Label getCurrent() {
        return current;
    }

    public void setCurrent(Label current) {
        this.current = current;
    }
}