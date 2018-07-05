package it.polimi.ingsw.gui.scenarios;

import it.polimi.ingsw.utils.Constants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class Disconnected extends VBox {

    private Button closeDef = new Button("Exit");

    public Disconnected(){
        super();
        Label mexa = new Label("SEI STATO DISCONNESSO. \nBYE.");
        mexa.setTextFill(Paint.valueOf(Constants.WHITE));
        mexa.setStyle("-fx-font-size: 50");
        setStyle("-fx-background-color: black");
        setAlignment(Pos.CENTER);
        setPadding(new Insets(600,600,600,600));
        getChildren().addAll(mexa, closeDef);
    }
}
