package it.polimi.ingsw.gui.scenarios;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Ranking extends GridPane{
    public Ranking() {
        Label lab = new Label("FINE PARTITA");
        add(lab, 0, 0, 2, 1);
        setStyle("-fx-background-color: #ff8080");
        setAlignment(Pos.CENTER);
        autosize();
        setVgap(20);
        setHgap(60);
        setPrefSize(700,450);
    }

}
