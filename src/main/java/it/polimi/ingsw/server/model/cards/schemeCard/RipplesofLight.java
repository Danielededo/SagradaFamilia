package it.polimi.ingsw.server.model.cards.schemeCard;

import it.polimi.ingsw.server.model.cards.GlassWindow;
import it.polimi.ingsw.utils.Colour;

public class RipplesofLight extends GlassWindow {
    public RipplesofLight(){
        super.setName("Ripples of Light");
        super.setLink(12);
        super.setDifficulty(5);
        super.setSlot(0, Colour.WHITE, 0, 0);
        super.setSlot(0, Colour.WHITE, 0, 1);
        super.setSlot(0, Colour.WHITE, 0, 2);
        super.setSlot(0, Colour.RED, 0, 3);
        super.setSlot(5, Colour.WHITE, 0, 4);
        super.setSlot(0, Colour.WHITE, 1, 0);
        super.setSlot(0, Colour.WHITE, 1, 1);
        super.setSlot(0, Colour.PURPLE, 1, 2);
        super.setSlot(4, Colour.WHITE, 1, 3);
        super.setSlot(0, Colour.BLUE, 1, 4);
        super.setSlot(0, Colour.WHITE, 2, 0);
        super.setSlot(0, Colour.BLUE, 2, 1);
        super.setSlot(3, Colour.WHITE, 2, 2);
        super.setSlot(0, Colour.YELLOW, 2, 3);
        super.setSlot(6, Colour.WHITE, 2, 4);
        super.setSlot(0, Colour.YELLOW, 3, 0);
        super.setSlot(2, Colour.WHITE, 3, 1);
        super.setSlot(0, Colour.GREEN, 3, 2);
        super.setSlot(1, Colour.WHITE, 3, 3);
        super.setSlot(0, Colour.RED, 3, 4);
    }
}
