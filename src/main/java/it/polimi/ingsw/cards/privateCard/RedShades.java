package it.polimi.ingsw.cards.privateCard;

import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.cards.PrivateObject;

public class RedShades extends PrivateObject {
    public RedShades() {
        super.setValue(1);
        super.setName("Red Shades");
        super.setColor(Colour.RED);
        super.setEffect("Sum of values on all red dice");
    }
}
