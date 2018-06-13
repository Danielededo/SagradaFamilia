package it.polimi.ingsw.server.model.cards.publicObject_Tests;

import it.polimi.ingsw.server.model.cards.GlassWindow;
import it.polimi.ingsw.server.model.cards.publicCard.DifferentColumnsColors;
import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Player;
import org.junit.jupiter.api.Test;

public class testDiversiColoriColonna_Test {

    @Test
    void testa_different_columnColors() {
        Player p= new Player("mario");
        GlassWindow vetrata = new GlassWindow();
        p.setWindow(vetrata);
        Slot s = new Slot(0, Colour.WHITE);
        Slot t = new Slot(0, Colour.WHITE);
        Slot u = new Slot(0, Colour.WHITE);
        Slot v = new Slot(0, Colour.WHITE);
        Slot z = new Slot(0, Colour.WHITE);
        Slot x = new Slot(0, Colour.WHITE);
        Die a = new Die();
        Die b = new Die();
        Die c = new Die();
        Die d = new Die();
        Die e = new Die();
        a.randomdado();
        a.setDicecolor(Colour.RED);
        b.randomdado();
        b.setDicecolor(Colour.BLUE);
        c.randomdado();
        c.setDicecolor(Colour.YELLOW);
        d.randomdado();
        d.setDicecolor(Colour.GREEN);
        e.randomdado();
        e.setDicecolor(Colour.PURPLE);
        it.polimi.ingsw.server.model.cards.PublicObject card = new DifferentColumnsColors();
        s.setDie(a);
        t.setDie(b);
        u.setDie(c);
        v.setDie(d);
        z.setDie(e);
        x.setDie(b);
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
                            "-" + p.getWindow().getSlot(i, j).getDice().getDicecolor() + "   ");
                } else System.out.print(p.getWindow().getSlot(i, j).isOccupate() + " ");
            }
            System.out.println();
        }
        System.out.println("punteggio: " + card.calcola_punteggio(p));
    }
}
