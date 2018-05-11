package it.polimi.ingsw.Game.Match_Tests;

import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.Scheme;
import it.polimi.ingsw.Dice.Sack;
import it.polimi.ingsw.Game.Match;
import it.polimi.ingsw.Game.Player;
import it.polimi.ingsw.Game.Round;
import it.polimi.ingsw.Game.Stock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class testStampa {
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
