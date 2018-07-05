package it.polimi.ingsw.gui.components;

import javafx.scene.control.Button;

public class Tools extends ComponentG {
    private Button button;
    private int value;

    public Tools(String nome, String perc){
        super(nome,perc);
        setFitWidth(220);
        setFitHeight(220);
        setPreserveRatio(true);
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}