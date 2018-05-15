package it.polimi.ingsw.cards.privateCard;

import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.cards.PrivateObject;

public class PurpleShades extends PrivateObject{
    public PurpleShades() {
        super.setValue(5);
        super.setName("Purple Shades");
        super.setColor(Colour.PURPLE);
        super.setEffect("Sum of values on all purple dice");
    }
}
