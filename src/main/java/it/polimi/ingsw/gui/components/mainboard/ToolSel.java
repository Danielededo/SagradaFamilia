package it.polimi.ingsw.gui.components.mainboard;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class ToolSel extends GridPane {

    ArrayList<Button> choosing = new ArrayList<>();

    public ToolSel(){
        super();
    }

    public ArrayList<Button> getChoosing() {
        return choosing;
    }
}
