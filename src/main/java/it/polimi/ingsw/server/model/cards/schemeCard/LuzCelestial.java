package it.polimi.ingsw.server.model.cards.schemeCard;

import it.polimi.ingsw.server.model.cards.GlassWindow;
import it.polimi.ingsw.utils.Colour;

public class LuzCelestial extends GlassWindow {
    public LuzCelestial(){
        super.setName("Luz Celestial");
        super.setLink(10);
        super.setDifficulty(3);
        super.setSlot(0, Colour.WHITE, 0, 0);
        super.setSlot(0, Colour.WHITE, 0, 1);
        super.setSlot(0, Colour.RED, 0, 2);
        super.setSlot(5, Colour.WHITE, 0, 3);
        super.setSlot(0, Colour.WHITE, 0, 4);
        super.setSlot(0, Colour.PURPLE, 1, 0);
        super.setSlot(4, Colour.WHITE, 1, 1);
        super.setSlot(0, Colour.WHITE, 1, 2);
        super.setSlot(0, Colour.GREEN, 1, 3);
        super.setSlot(3, Colour.WHITE, 1, 4);
        super.setSlot(6, Colour.WHITE, 2, 0);
        super.setSlot(0, Colour.WHITE, 2, 1);
        super.setSlot(0, Colour.WHITE, 2, 2);
        super.setSlot(0, Colour.BLUE, 2, 3);
        super.setSlot(0, Colour.WHITE, 2, 4);
        super.setSlot(0, Colour.WHITE, 3, 0);
        super.setSlot(0, Colour.YELLOW, 3, 1);
        super.setSlot(2, Colour.WHITE, 3, 2);
        super.setSlot(0, Colour.WHITE, 3, 3);
        super.setSlot(0, Colour.WHITE, 3, 4);
    }
}
