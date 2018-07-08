package it.polimi.ingsw.gui.components.mainboard;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class ToolSel extends GridPane {

    ArrayList<Button> choosing = new ArrayList<>();
    ArrayList<Label> signal = new ArrayList<>();

    public ToolSel(){
        super();
        int i=0;
        while(i<3){
            Label lab =new Label("Segnalini necessari: 1");
            signal.add(lab);
            add(signal.get(signal.size()-1),i,2);
            i++;
        }
    }

    public ArrayList<Label> getSignal() {
        return signal;
    }

    public ArrayList<Button> getChoosing() {
        return choosing;
    }
}
