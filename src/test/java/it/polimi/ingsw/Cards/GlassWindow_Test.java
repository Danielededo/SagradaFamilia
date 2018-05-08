package it.polimi.ingsw.Cards;

import it.polimi.ingsw.Cards.SchemeCard.SunCatcher;
import it.polimi.ingsw.Cards.SchemeCard.Virtus;
import it.polimi.ingsw.Cards.SchemeCard.WaterofLife;
import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Dice.Die;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlassWindow_Test {
    @Test
    void testcreateWindow() {
        GlassWindow c = new SunCatcher();
        Assertions.assertEquals(3, c.getSlot(3, 1).getValue());
        Assertions.assertEquals(Colour.WHITE, c.getSlot(3, 1).getSlotcolour());
        System.out.println(c.toString());
    }

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
