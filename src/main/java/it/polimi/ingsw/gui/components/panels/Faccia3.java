package it.polimi.ingsw.gui.components.panels;

import it.polimi.ingsw.utils.Constants;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Paint;

public class Faccia3 extends Canvas{

    private int num = 3;

    public Faccia3(double w, double h){
        super(w,h);
        this.getGraphicsContext2D().setFill(Paint.valueOf(Constants.BLACK));
        this.getGraphicsContext2D().fillOval(Constants.NE3_BOX_X,Constants.NE3_BOX_Y,Constants.R_DOT, Constants.R_DOT);
        this.getGraphicsContext2D().fillOval(Constants.C_BOX_X,Constants.C_BOX_Y,Constants.R_DOT, Constants.R_DOT);
        this.getGraphicsContext2D().fillOval(Constants.SO3_BOX_X,Constants.SO3_BOX_Y,Constants.R_DOT, Constants.R_DOT);
    }

    public int getNum() {
        return num;
    }
}
