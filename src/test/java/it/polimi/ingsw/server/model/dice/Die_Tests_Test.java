package it.polimi.ingsw.server.model.dice;

import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.model.cards.GlassWindow;
import it.polimi.ingsw.server.model.cards.schemeCard.Comitas;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Die_Tests_Test {
    @Test
    void setDice() {
        Die a = new Die();
        Die b = new Die();
        GlassWindow comitas = new Comitas();
        a.randomdado();
        System.out.println(a.getFace());
        b.randomdado();
        System.out.println(b.getFace());
        a.setDicecolor(Colour.YELLOW);
        b.setDicecolor(Colour.YELLOW);
        comitas.getSlot(2,3).setDie(a);
        comitas.getSlot(2, 3).setDie(b);
        System.out.println(comitas.getSlot(2, 3).getDice());
        assertEquals(a, comitas.getSlot(2, 3).getDice());
    }
}
