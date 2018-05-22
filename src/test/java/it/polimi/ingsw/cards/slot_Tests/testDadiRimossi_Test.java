package it.polimi.ingsw.cards.slot_Tests;

import it.polimi.ingsw.cards.GlassWindow;
import it.polimi.ingsw.cards.schemeCard.Battlo;
import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.dice.Die;
import org.junit.jupiter.api.Test;

public class testDadiRimossi_Test {
    @Test
    void testdiceremove() {
        GlassWindow window = new Battlo();
        Die a = new Die();
        a.randomdado();
        System.out.println(a.getFace());
        a.setDicecolor(Colour.YELLOW);
        Die b = new Die();
        b.randomdado();
        System.out.println(b.getFace());
        b.setDicecolor(Colour.YELLOW);
        window.getSlot(2, 2).setDie(a);
        window.getSlot(2, 2).setDie(b);
        System.out.println(window.getSlot(2, 2).getDice());
        window.getSlot(2, 2).removeDie();
        window.getSlot(2, 2).setDie(b);
        System.out.println(window.getSlot(2, 2).getDice());
    }
}
