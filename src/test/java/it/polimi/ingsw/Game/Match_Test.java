package it.polimi.ingsw.Game;

import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.Scheme;
import it.polimi.ingsw.Dice.Sack;
import it.polimi.ingsw.Cards.SchemeCard.Battlo;
import it.polimi.ingsw.Cards.SchemeCard.Virtus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class Match_Test {
    @Test
    void rotateturn2Players() {
        GlassWindow windowA = new Battlo();
        GlassWindow windowB = new Virtus();
        Player a = new Player("mario");
        Player b = new Player("daniele");
        a.setWindow(windowA);
        b.setWindow(windowB);
        Match game = new Match(a,b);
        Round round = new Round(game);
        Stock stock = new Stock();
        Sack sacktest = new Sack();
        stock.setDicestock(sacktest.extractfromSack(game));
        stock.show_riserva();
        int z = 0;
        for (int i = 0; i < 2 * game.getnumberPlayers(); i++) {
            round.getTurns().get(i).getOneplayer().getWindow().getSlot(2, 3).setDie(stock.extract_die(z));
        }
        System.out.println(a.getWindow().getSlot(2, 3).getDice());
        System.out.println(b.getWindow().getSlot(2, 3).getDice());
    }

    @Test
    void print() {
        Player c = new Player("Daniele");
        Player d = new Player("Mario");
        Match game = new Match(c, d);
        Round round = new Round(game);
        Sack sacktest = new Sack();
        Stock stock = new Stock();
        ArrayList<GlassWindow> b;
        Scheme a = new Scheme();
        b = a.extractGlass();
        System.out.println(b);
        c.setWindow(b.get(3));
        b = a.extractGlass();
        System.out.println(b);
        d.setWindow(b.get(2));
        System.out.println(c.getWindow());
        System.out.println(d.getWindow());
    }
}
