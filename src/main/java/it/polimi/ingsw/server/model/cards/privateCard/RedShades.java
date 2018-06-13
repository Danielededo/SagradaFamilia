package it.polimi.ingsw.server.model.cards.privateCard;

import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.model.cards.PrivateObject;

public class RedShades extends PrivateObject {
    public RedShades() {
        super.setValue(1);
        super.setName("Red Shades");
        super.setColor(Colour.RED);
        super.setEffect("Sum of values on all red dice");
    }
}
