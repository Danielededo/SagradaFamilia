package it.polimi.ingsw.Cards.PrivateObject_Test;

import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.PrivateCard.BlueShades;
import it.polimi.ingsw.Cards.SchemeCard.ViaLux;
import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Dice.Die;
import it.polimi.ingsw.Game.Player;
import org.junit.jupiter.api.Test;

public class testCalcoloScore {
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
