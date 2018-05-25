package it.polimi.ingsw.cards.glasswindow_Tests;

import it.polimi.ingsw.cards.GlassWindow;
import it.polimi.ingsw.cards.schemeCard.SunCatcher;
import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.dice.Die;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class testCreateWindow_Test {

    @Test
    void testCreateWindow() {
        GlassWindow c = new SunCatcher();
        Assertions.assertEquals(3, c.getSlot(3, 1).getValue());
        Assertions.assertEquals(Colour.WHITE, c.getSlot(3, 1).getSlotcolour());
        Die a=new Die(6,Colour.PURPLE);
        c.getSlot(2,4).setDie(a);
        System.out.println(c.toString());
    }
}
