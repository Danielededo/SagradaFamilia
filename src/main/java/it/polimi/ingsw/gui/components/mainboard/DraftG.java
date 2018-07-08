package it.polimi.ingsw.gui.components.mainboard;

import it.polimi.ingsw.gui.components.panels.DieG;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class DraftG extends GridPane {

    private ArrayList<DieG> draftie = new ArrayList<>();

    public DraftG(){
        super();
        Label title = new Label("RISERVA");
        add(title,0,0);

        setHgap(20);

    }

    public ArrayList<DieG> getDraftie() {
        return draftie;
    }

}
