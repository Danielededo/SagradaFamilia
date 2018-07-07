package it.polimi.ingsw.gui.scenarios;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class Ranking extends GridPane{
    public Ranking() {
        setStyle("-fx-back-color: #ff8080");
        setAlignment(Pos.CENTER);
        autosize();
        setPrefSize(700,450);
    }

}
