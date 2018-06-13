package it.polimi.ingsw.server.model.cards.glasswindow_Tests;

import it.polimi.ingsw.server.model.cards.GlassWindow;
import it.polimi.ingsw.server.model.cards.schemeCard.WaterofLife;
import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.model.dice.Die;
import org.junit.jupiter.api.Test;

public class testRiempimentoWindow_Test {

    @Test
    void testRiempimento() {
        GlassWindow window = new WaterofLife();
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 4; j++) {
                Die a = new Die();
                a.randomdado();
                System.out.print(a.getFace() + "     ");
                a.setDicecolor(Colour.BLUE);
                window.getSlot(i, j).setDie(a);
            }
            System.out.println();
        }
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 4; j++) {
                System.out.print(window.getSlot(i, j).isOccupate() + "   ");
            }
            System.out.println();
        }
    }
}
