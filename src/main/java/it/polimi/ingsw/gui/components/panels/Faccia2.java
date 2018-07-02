package it.polimi.ingsw.gui.components.panels;

import it.polimi.ingsw.server.utils.Constants;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Paint;

public class Faccia2 extends Canvas{

    private int num = 2;

        public Faccia2(double w, double h){
            super(w,h);
            this.getGraphicsContext2D().setFill(Paint.valueOf(Constants.BLACK));
            this.getGraphicsContext2D().fillOval(Constants.NE2_BOX_X, Constants.NE2_BOX_Y,Constants.R_DOT,Constants.R_DOT);
            this.getGraphicsContext2D().fillOval(Constants.SO2_BOX_X,Constants.SO2_BOX_Y,Constants.R_DOT,Constants.R_DOT);

        }


    public int getNum() {
        return num;
    }
}

