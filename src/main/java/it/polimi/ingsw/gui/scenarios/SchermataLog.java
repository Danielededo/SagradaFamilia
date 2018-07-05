package it.polimi.ingsw.gui.scenarios;

import it.polimi.ingsw.utils.Constants;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class SchermataLog extends VBox{

    private Button click = new Button("Log in");
    private TextField nickInput = new TextField();
    private PasswordField passInput = new PasswordField();
    private Label mex = new Label("");


    public SchermataLog(){
        super();
        setAlignment(Pos.CENTER);
        setVisible(true);
        setStyle("-fx-background-color: black");

        Image logo = new Image("/images/logoscritta.png");
        ImageView logoview = new ImageView(logo);
        logoview.setFitHeight(270);
        logoview.setFitWidth(712);
        logoview.setPreserveRatio(true);

        GridPane griglialog = new GridPane();
        griglialog.setVgap(8);
        griglialog.setHgap(10);
        griglialog.setStyle("-fx-background-color: " + Constants.BLACK);
        griglialog.setAlignment(Pos.CENTER);

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

        mex.setFont(Font.font("Times New Roman"));
        mex.centerShapeProperty();
        mex.setStyle("-fx-font-size: " + Constants.TITLE_SIZE);
        mex.setTextFill(Paint.valueOf(Constants.WHITE));

        mex.setVisible(true);

        setSpacing(20);
        getChildren().addAll(logoview, griglialog, mex);
    }

    /*public void start(Stage primaryStage) {


        primaryStage.setOnCloseRequest(event -> {
            System.exit(-1);
        });
        primaryStage.setScene(new Scene(log));
        //primaryStage.setFullScreen(true);
        primaryStage.show();

    }*/


    public Button getClick() {
        return click;
    }

    public void setClick(Button click) {
        this.click = click;
    }

    public TextField getNickInput() {
        return nickInput;
    }

    public void setNickInput(TextField nickInput) {
        this.nickInput = nickInput;
    }

    public PasswordField getPassInput() {
        return passInput;
    }

    public void setPassInput(PasswordField passInput) {
        this.passInput = passInput;
    }

    public Label getMex() {
        return mex;
    }

    public void setMex(Label mex) {
        this.mex = mex;
    }

}
