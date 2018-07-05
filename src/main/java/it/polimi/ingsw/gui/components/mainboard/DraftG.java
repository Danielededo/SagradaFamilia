package it.polimi.ingsw.gui.components.mainboard;

import it.polimi.ingsw.gui.components.panels.DieG;
import it.polimi.ingsw.gui.components.panels.FacciaVuota;
import it.polimi.ingsw.utils.Constants;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class DraftG extends GridPane {



    private ArrayList<DieG> draftie = new ArrayList<>();

    private DieG selected = new DieG(new FacciaVuota(Constants.SLOT, Constants.SLOT));

    public DraftG(){
        super();
        Label title = new Label("RISERVA");
        add(title,0,0);

        setHgap(20);

    }

    public DieG getSelected() {
        return selected;
    }

    public void setSelected(DieG selected) {
        this.selected = selected;
    }

    public ArrayList<DieG> getDraftie() {
        return draftie;
    }



}
