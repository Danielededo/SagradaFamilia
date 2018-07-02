package it.polimi.ingsw.gui.components.mainboard;

import it.polimi.ingsw.gui.components.panels.FacciaVuota;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class RoundtrackG extends HBox {

    private StackPane round1 = new StackPane();
    private StackPane round2 = new StackPane();
    private StackPane round3 = new StackPane();
    private StackPane round4 = new StackPane();
    private StackPane round5 = new StackPane();
    private StackPane round6 = new StackPane();
    private StackPane round7 = new StackPane();
    private StackPane round8 = new StackPane();
    private StackPane round9 = new StackPane();
    private StackPane round10 = new StackPane();

    public RoundtrackG(){
        super();
        Label title = new Label("Roundtrack");
        round1.getChildren().add(new FacciaVuota(50,50));
        round2.getChildren().add(new FacciaVuota(50,50));
        round3.getChildren().add(new FacciaVuota(50,50));
        round4.getChildren().add(new FacciaVuota(50,50));
        round5.getChildren().add(new FacciaVuota(50,50));
        round6.getChildren().add(new FacciaVuota(50,50));
        round7.getChildren().add(new FacciaVuota(50,50));
        round8.getChildren().add(new FacciaVuota(50,50));
        round9.getChildren().add(new FacciaVuota(50,50));
        round10.getChildren().add(new FacciaVuota(50,50));
        getChildren().addAll(title, round1, round2, round3, round4, round5, round6, round7, round8, round9, round10);
    }
}
