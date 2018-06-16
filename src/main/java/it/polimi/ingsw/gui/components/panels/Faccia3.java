package it.polimi.ingsw.gui.components.panels;

import it.polimi.ingsw.server.utils.Constants;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Paint;

public class Faccia3 extends Canvas{

    public Faccia3(double w, double h){
        super(w,h);
        this.getGraphicsContext2D().setFill(Paint.valueOf(Constants.BLACK));
        this.getGraphicsContext2D().fillOval(11,11,Constants.R_DOT, Constants.R_DOT);
        this.getGraphicsContext2D().fillOval(39,39,Constants.R_DOT, Constants.R_DOT);
        this.getGraphicsContext2D().fillOval(66,66,Constants.R_DOT, Constants.R_DOT);
    }
}
