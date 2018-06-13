package it.polimi.ingsw.server.model.cards.privateCard;

import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.model.cards.PrivateObject;

public class BlueShades extends PrivateObject{
    public BlueShades() {
        super.setValue(4);
        super.setName("Blue Shades");
        super.setColor(Colour.BLUE);
        super.setEffect("Sum of values on all blue dice");
    }
}
