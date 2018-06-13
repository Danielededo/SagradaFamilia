package it.polimi.ingsw.server.model.game.match_Tests;

import it.polimi.ingsw.server.model.cards.GlassWindow;
import it.polimi.ingsw.server.model.cards.Scheme;
import it.polimi.ingsw.server.model.dice.Sack;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Player;
import it.polimi.ingsw.server.model.game.Round;
import it.polimi.ingsw.server.model.game.Stock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class testStampa_Test {
    @Test
    void print() {
        Player c = new Player("Daniele");
        Player d = new Player("Mario");
        Match game = new Match(c, d);
        Round round = new Round(game);
        Sack sacktest = new Sack();
        Stock stock = new Stock();
        stock.setDicestock(sacktest.extractfromSack(game));
        ArrayList<GlassWindow> b;
        Scheme a = new Scheme();
        System.out.println(stock);
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
