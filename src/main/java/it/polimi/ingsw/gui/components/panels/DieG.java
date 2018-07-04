package it.polimi.ingsw.gui.components.panels;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;


public class DieG extends StackPane{
    private String colour;
    private Canvas face;
    private int pos;
    private Button button = new Button();

    public DieG(Canvas tel){
        super();
        face = tel;
        this.getChildren().addAll(face);

    }


    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Canvas getFace() {
        return face;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
        this.setStyle("-fx-background-color: " + colour);
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
