package it.polimi.ingsw.Cards.Glasswindow_Tests;

import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.SchemeCard.Virtus;
import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Dice.Die;
import org.junit.jupiter.api.Test;

public class testInserimento {
    @Test
    void testWindow() {
        GlassWindow virtus = new Virtus();
        Die dado = new Die();
        dado.randomdado();
        dado.setDicecolor(Colour.GREEN);
        System.out.println(dado.getFace());
        System.out.println(virtus.getSlot(3, 1).isOccupate());
        virtus.getSlot(3, 1).setDie(dado);
        System.out.println(virtus.getSlot(3, 1).isOccupate());
    }
}
