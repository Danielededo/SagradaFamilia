package Cards;

import Dice.Colour;

public class BlueShades extends PrivateObject{
    public BlueShades() {
        super.setValue(4);
        super.setName("Blue Shades");
        super.setColor(Colour.BLUE);
        super.setEffect("Sum of values on all blue dice");
    }
}
