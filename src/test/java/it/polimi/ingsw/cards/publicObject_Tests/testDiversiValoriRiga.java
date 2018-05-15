package it.polimi.ingsw.cards.publicObject_Tests;

import it.polimi.ingsw.cards.GlassWindow;
import it.polimi.ingsw.cards.publicCard.DifferentRowShades;
import it.polimi.ingsw.cards.PublicObject;
import it.polimi.ingsw.cards.Slot;
import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.dice.Die;
import it.polimi.ingsw.game.Player;
import org.junit.jupiter.api.Test;

public class testDiversiValoriRiga {
    @Test
    void testadifferent_rowShades() {
        Player p= new Player("mario");
        PublicObject card = new DifferentRowShades();
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
        for (int i = 1; i < 3; i++) {
            p.getWindow().setSlot(s, i, 0);
            p.getWindow().setSlot(t, i, 1);
            p.getWindow().setSlot(u, i, 2);
            p.getWindow().setSlot(v, i, 3);
            p.getWindow().setSlot(z, i, 4);
        }
        p.getWindow().setSlot(x, 0, 0);
        p.getWindow().setSlot(v, 0, 1);
        p.getWindow().setSlot(t, 0, 2);
        p.getWindow().setSlot(z, 0, 3);
        p.getWindow().setSlot(u, 0, 4);

        p.getWindow().setSlot(x, 3, 0);
        p.getWindow().setSlot(s, 3, 1);
        p.getWindow().setSlot(x, 3, 2);
        p.getWindow().setSlot(v, 3, 3);
        p.getWindow().setSlot(t, 3, 4);
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
