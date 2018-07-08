package it.polimi.ingsw.gui.components.mainboard;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class ToolSel extends GridPane {

    ArrayList<Button> choosing = new ArrayList<>();

    public ToolSel(){
        super();
        Label label = new Label("Segnalini necessari: 1");
        Label labe = new Label("Segnalini necessari: 1");
        Label lab = new Label("Segnalini necessari: 1");
        add(label, 0, 2);
        add(labe, 1, 2);
        add(lab, 2, 2);

    }

    public ArrayList<Button> getChoosing() {
        return choosing;
    }
}
