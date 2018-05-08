package it.polimi.ingsw.Cards.PrivateCard;

import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Cards.PrivateObject;

public class YellowShades extends PrivateObject {
    public YellowShades() {
        super.setValue(2);
        super.setName("Yellow Shades");
        super.setColor(Colour.YELLOW);
        super.setEffect("Sum of values on all yellow dice");
    }
}
