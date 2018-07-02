package it.polimi.ingsw.gui.components.panels;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class Tassel extends StackPane {

    private Button button = new Button();
    private int value;
    private String color;

    public Tassel(int i){
        super();
        value = i;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
