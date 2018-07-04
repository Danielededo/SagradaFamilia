package it.polimi.ingsw.gui.components.mainboard;

import it.polimi.ingsw.server.utils.Constants;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class RoundtrackG extends GridPane {

    public ArrayList<GridPane> list = new ArrayList<>();


    public RoundtrackG(){
        super();
        Label title = new Label("Roundtrack");
        getChildren().add(title);
        for(int i = 0; i < Constants.F_DIE; i++){
            list.add(i, new GridPane());
            getChildren().add(list.get(i));
        }
    }
}
