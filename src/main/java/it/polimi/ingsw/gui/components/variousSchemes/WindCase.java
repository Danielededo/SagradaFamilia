package it.polimi.ingsw.gui.components.variousSchemes;

import it.polimi.ingsw.server.utils.Constants;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class WindCase extends Canvas {

    private Windows scelta;
    private StackPane lay = new StackPane();

    public WindCase(double w, double h){

        super(w,h);

        getGraphicsContext2D().setLineWidth(7);

        getGraphicsContext2D().strokeLine(Constants.PADD_CASE,Constants.PADD_CASE,Constants.PADD_CASE,h - Constants.PADD_CASE);
        getGraphicsContext2D().strokeLine(Constants.PADD_CASE,h - Constants.PADD_CASE,w - Constants.PADD_CASE,h - Constants.PADD_CASE);
        getGraphicsContext2D().strokeLine(w - Constants.PADD_CASE,h - Constants.PADD_CASE,w - Constants.PADD_CASE,Constants.PADD_CASE);
        getGraphicsContext2D().strokeLine(w - Constants.PADD_CASE, Constants.PADD_CASE, Constants.PADD_CASE, Constants.PADD_CASE);
        getGraphicsContext2D().strokeLine(w - Constants.PADD_CASE, Constants.PADD_CASE + 50, Constants.PADD_CASE, Constants.PADD_CASE + 50);

    }

    public void addChosen(Windows choice){
        this.scelta = choice;
        Label nameCase = new Label(choice.getName());
        nameCase.setStyle("-fx-font-family: Times New Roman");
        nameCase.setStyle("-fx-font-size: 28");
        lay = new StackPane();

        lay.getChildren().add(this);
        lay.getChildren().add(choice);
        lay.getChildren().add(nameCase);
        lay.setMargin(choice, Constants.INS_WIND);
        lay.setMargin(nameCase, Constants.INS_LAB);
    }

    public Windows getScelta() {
        return scelta;
    }

    public StackPane getLay() {
        return lay;
    }
}
