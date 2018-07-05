package it.polimi.ingsw.gui.components.panels;

import it.polimi.ingsw.utils.Constants;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Paint;

public class Faccia6 extends Canvas{

    private int num = 6;

    public Faccia6(double w, double h){
        super(w,h);
        this.getGraphicsContext2D().setFill(Paint.valueOf(Constants.BLACK));
        this.getGraphicsContext2D().fillOval(Constants.BOX6_X1,Constants.BOX6_Y1, Constants.R_DOT,Constants.R_DOT);
        this.getGraphicsContext2D().fillOval(Constants.BOX6_X2,Constants.BOX6_Y1, Constants.R_DOT,Constants.R_DOT);
        this.getGraphicsContext2D().fillOval(Constants.BOX6_X1,Constants.BOX6_Y2,Constants.R_DOT,Constants.R_DOT);
        this.getGraphicsContext2D().fillOval(Constants.BOX6_X2,Constants.BOX6_Y2,Constants.R_DOT,Constants.R_DOT);
        this.getGraphicsContext2D().fillOval(Constants.BOX6_X1,Constants.BOX6_Y3,Constants.R_DOT,Constants.R_DOT);
        this.getGraphicsContext2D().fillOval(Constants.BOX6_X2,Constants.BOX6_Y3,Constants.R_DOT,Constants.R_DOT);
    }

    public int getNum() {
        return num;
    }
}
