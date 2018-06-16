package it.polimi.ingsw.gui.components.panels;

import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class Dado {
    private String colour;
    ArrayList<StackPane> faces = new ArrayList<>();

    public Dado(String val){
        this.colour = val;
        int i = 6;
        while(i > 0){
            StackPane prov = new StackPane();
            prov.setStyle("-fx-background-color:" + val + ";");


        }
    }
}
