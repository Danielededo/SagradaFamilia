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

    public StackPane addChosen(Windows choice){
        this.scelta = choice;
        Label nameCase = new Label(choice.getName());
        nameCase.setStyle("-fx-font-family: Times New Roman");
        nameCase.setStyle("-fx-font-size: 28");
        StackPane layout = new StackPane();

        layout.getChildren().add(this);
        layout.getChildren().add(choice);
        layout.getChildren().add(nameCase);
        layout.setMargin(choice, Constants.INS_WIND);
        layout.setMargin(nameCase, Constants.INS_LAB);

        return layout;
    }

    public Windows getScelta() {
        return scelta;
    }
}
