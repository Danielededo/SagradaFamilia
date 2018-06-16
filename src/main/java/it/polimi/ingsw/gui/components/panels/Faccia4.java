package it.polimi.ingsw.gui.components.panels;

import it.polimi.ingsw.server.utils.Constants;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Paint;

public class Faccia4 extends Canvas {

    public Faccia4(double w, double h){
        super(w,h);
        this.getGraphicsContext2D().setFill(Paint.valueOf(Constants.BLACK));
        this.getGraphicsContext2D().fillOval(18,18,Constants.R_DOT,Constants.R_DOT);
        this.getGraphicsContext2D().fillOval(60,18,Constants.R_DOT,Constants.R_DOT);
        this.getGraphicsContext2D().fillOval(18,60,Constants.R_DOT,Constants.R_DOT);
        this.getGraphicsContext2D().fillOval(60,60,Constants.R_DOT,Constants.R_DOT);

    }
}
