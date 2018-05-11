package it.polimi.ingsw.Cards.Glasswindow_Tests;

import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.SchemeCard.SunCatcher;
import it.polimi.ingsw.Dice.Colour;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class testCreateWindow {

    @Test
    void testCreateWindow() {
        GlassWindow c = new SunCatcher();
        Assertions.assertEquals(3, c.getSlot(3, 1).getValue());
        Assertions.assertEquals(Colour.WHITE, c.getSlot(3, 1).getSlotcolour());
        System.out.println(c.toString());
    }
}
