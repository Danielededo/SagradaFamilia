package it.polimi.ingsw.Cards;

import it.polimi.ingsw.Cards.SchemeCard.Battlo;
import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Dice.Die;
import org.junit.jupiter.api.Test;

public class Slot_test {
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
