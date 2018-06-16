package it.polimi.ingsw.gui.components.panels;

import it.polimi.ingsw.server.utils.Constants;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Paint;

public class Faccia1 extends Canvas {


    public Faccia1(double w, double h){
        super(w,h);
        this.getGraphicsContext2D().setFill(Paint.valueOf(Constants.BLACK));
        this.getGraphicsContext2D().fillOval(39, 39, 22,22);
    }
}
