package it.polimi.ingsw.gui.components.panels;

import it.polimi.ingsw.utils.Constants;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Paint;

public class TokenG extends Canvas{

    public TokenG(double w, double h){
        super(w,h);
        getGraphicsContext2D().setFill(Paint.valueOf(Constants.BLACK));
        getGraphicsContext2D().fillOval(7,7,15,15);
    }

}
