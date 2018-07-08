package it.polimi.ingsw.gui.components.mainboard;

import it.polimi.ingsw.gui.components.panels.DieG;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class RoundtrackG extends GridPane {

    private ArrayList<DieG> list = new ArrayList<>();


    public RoundtrackG(){
        super();
        //Label title = new Label("Roundtrack");
        //add(title, 0,0, 20,1);
        setHgap(10);
    }

    public ArrayList<DieG> getList() {
        return list;
    }
}
