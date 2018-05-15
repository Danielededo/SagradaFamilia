package it.polimi.ingsw.cards.publicObject_Tests;

import it.polimi.ingsw.cards.GlassWindow;
import it.polimi.ingsw.cards.publicCard.DifferentColumnShades;
import it.polimi.ingsw.cards.PublicObject;
import it.polimi.ingsw.cards.Slot;
import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.dice.Die;
import it.polimi.ingsw.game.Player;
import org.junit.jupiter.api.Test;

public class testDiversiValoriColonna {
    @Test
    void testadifferent_columnShades() {
        Player p = new Player("mario");
        PublicObject card = new DifferentColumnShades();
        GlassWindow vetrata = new GlassWindow();
        p.setWindow(vetrata);
        Slot s = new Slot(0, Colour.WHITE);
        Slot t = new Slot(0, Colour.WHITE);
        Slot u = new Slot(0, Colour.WHITE);
        Slot v = new Slot(0, Colour.WHITE);
        Slot z = new Slot(0, Colour.WHITE);
        Slot x = new Slot(0, Colour.WHITE);
        Die a = new Die(1, Colour.RED);
        Die b = new Die(2, Colour.PURPLE);
        Die c = new Die(3, Colour.YELLOW);
        Die d = new Die(4, Colour.GREEN);
        Die e = new Die(5, Colour.BLUE);
        Die f = new Die(6, Colour.RED);
        s.setDie(a);
        t.setDie(b);
        u.setDie(c);
        v.setDie(d);
        z.setDie(e);
        x.setDie(f);
        for (int i = 1; i < 4; i++) {
            p.getWindow().setSlot(s, 0, i);
            p.getWindow().setSlot(t, 1, i);
            p.getWindow().setSlot(u, 2, i);
            p.getWindow().setSlot(v, 3, i);
        }
        p.getWindow().setSlot(s, 0, 0);
        p.getWindow().setSlot(t, 1, 0);
        p.getWindow().setSlot(x, 2, 0);
        p.getWindow().setSlot(v, 3, 0);

        p.getWindow().setSlot(s, 0, 4);
        p.getWindow().setSlot(t, 1, 4);
        p.getWindow().setSlot(s, 2, 4);
        p.getWindow().setSlot(z, 3, 4);
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 4; j++) {
                if (p.getWindow().getSlot(i, j).isOccupate()) {
                    System.out.print(p.getWindow().getSlot(i, j).isOccupate() +
                            "-" + p.getWindow().getSlot(i, j).getDice().getFace() + "   ");
                } else System.out.print(p.getWindow().getSlot(i, j).isOccupate() + " ");
            }
            System.out.println();
        }
        System.out.println(card.calcola_punteggio(p));
    }
}
