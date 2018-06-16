package it.polimi.ingsw.gui.components.panels;

import it.polimi.ingsw.server.utils.Constants;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Paint;

public class Faccia6 extends Canvas{

    public Faccia6(double w, double h){
        super(w,h);
        this.getGraphicsContext2D().setFill(Paint.valueOf(Constants.BLACK));
        this.getGraphicsContext2D().fillOval(17,8, Constants.R_DOT,Constants.R_DOT);
        this.getGraphicsContext2D().fillOval(59,8, Constants.R_DOT,Constants.R_DOT);
        this.getGraphicsContext2D().fillOval(17,39,Constants.R_DOT,Constants.R_DOT);
        this.getGraphicsContext2D().fillOval(59,39,Constants.R_DOT,Constants.R_DOT);
        this.getGraphicsContext2D().fillOval(17,70,Constants.R_DOT,Constants.R_DOT);
        this.getGraphicsContext2D().fillOval(59,70,Constants.R_DOT,Constants.R_DOT);
    }
}
