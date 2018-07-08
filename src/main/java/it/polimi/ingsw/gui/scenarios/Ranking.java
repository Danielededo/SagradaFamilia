package it.polimi.ingsw.gui.scenarios;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

public class Ranking extends GridPane{
    public Ranking() {
        Label lab = new Label("FINE PARTITA");
        lab.setStyle("-fx-font-size: 30");
        lab.setTextFill(Paint.valueOf("#0066ff"));
        add(lab, 0, 0, 4, 1);
        setStyle("-fx-background-color: #ccffff");
        setAlignment(Pos.CENTER);
        autosize();
        setVgap(20);
        setHgap(60);
        setPrefSize(700,450);
    }

}
