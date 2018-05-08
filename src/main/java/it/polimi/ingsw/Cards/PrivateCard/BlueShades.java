package it.polimi.ingsw.Cards.PrivateCard;

import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Cards.PrivateObject;

public class BlueShades extends PrivateObject{
    public BlueShades() {
        super.setValue(4);
        super.setName("Blue Shades");
        super.setColor(Colour.BLUE);
        super.setEffect("Sum of values on all blue dice");
    }
}
