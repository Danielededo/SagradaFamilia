package it.polimi.ingsw.server.model.cards.publicObject_Tests;

import it.polimi.ingsw.server.model.cards.GlassWindow;
import it.polimi.ingsw.server.model.cards.publicCard.DifferentShades;
import it.polimi.ingsw.server.model.cards.PublicObject;
import it.polimi.ingsw.server.model.cards.schemeCard.AuroraeMagnificus;
import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Player;
import org.junit.jupiter.api.Test;

public class testClearShades_Test {
    @Test
    void test_clear_shades() {
        Player p = new Player("mario");
        PublicObject card = new DifferentShades();
        GlassWindow window = new AuroraeMagnificus();
        p.setWindow(window);
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
        System.out.println(card.calcola_punteggio(p));
    }
}
