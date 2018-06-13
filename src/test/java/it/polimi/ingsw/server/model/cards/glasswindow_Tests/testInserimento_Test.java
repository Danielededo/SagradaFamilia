package it.polimi.ingsw.server.model.cards.glasswindow_Tests;

import it.polimi.ingsw.server.model.cards.GlassWindow;
import it.polimi.ingsw.server.model.cards.schemeCard.Virtus;
import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.model.dice.Die;
import org.junit.jupiter.api.Test;

public class testInserimento_Test {
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
