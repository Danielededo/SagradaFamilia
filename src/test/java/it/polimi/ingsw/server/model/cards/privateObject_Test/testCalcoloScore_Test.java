package it.polimi.ingsw.server.model.cards.privateObject_Test;

import it.polimi.ingsw.server.model.cards.GlassWindow;
import it.polimi.ingsw.server.model.cards.privateCard.BlueShades;
import it.polimi.ingsw.server.model.cards.schemeCard.ViaLux;
import it.polimi.ingsw.utils.Colour;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Player;
import org.junit.jupiter.api.Test;

public class testCalcoloScore_Test {
    @Test
    void testacalculate_score() {
        GlassWindow luce = new ViaLux();
        BlueShades privata = new BlueShades();
        Player p= new Player("mario");
        p.setPrivatetarget(privata);
        p.setWindow(luce);
        int sum;
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 4; j++) {
                Die a = new Die();
                a.randomdado();
                System.out.print(a.getFace() + "     ");
                a.setDicecolor(Colour.BLUE);
                p.getWindow().getSlot(i, j).setDie(a);
            }
            System.out.println();
        }
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 4; j++) {
                System.out.print(p.getWindow().getSlot(i, j).isOccupate() + "   ");
            }
            System.out.println();
        }
        System.out.println(p.getPrivatetarget().toString());
        sum = privata.calculate_score(p);
        System.out.println("somma: " + sum);
    }
}
