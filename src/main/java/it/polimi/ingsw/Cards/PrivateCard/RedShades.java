package it.polimi.ingsw.Cards.PrivateCard;

import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Cards.PrivateObject;

public class RedShades extends PrivateObject {
    public RedShades() {
        super.setValue(1);
        super.setName("Red Shades");
        super.setColor(Colour.RED);
        super.setEffect("Sum of values on all red dice");
    }
}
