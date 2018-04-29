package Cards;

import Dice.Colour;

public class YellowShades extends PrivateObject {
    public YellowShades() {
        super.setValue(2);
        super.setName("Yellow Shades");
        super.setColor(Colour.YELLOW);
        super.setEffect("Sum of values on all yellow dice");
    }
}
