package it.polimi.ingsw.server.model.game.match_Tests;

import it.polimi.ingsw.server.model.cards.GlassWindow;
import it.polimi.ingsw.server.model.cards.schemeCard.Battlo;
import it.polimi.ingsw.server.model.cards.schemeCard.Virtus;
import it.polimi.ingsw.server.model.dice.Sack;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Player;
import it.polimi.ingsw.server.model.game.Round;
import it.polimi.ingsw.server.model.game.Stock;
import org.junit.jupiter.api.Test;

public class RuotaTurni2_Test{
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
        stock.show_stock();
        int z = 0;
        for (int i = 0; i < 2 * game.getnumberPlayers(); i++) {
            round.getTurns().get(i).getOneplayer().getWindow().getSlot(2, 3).setDie(stock.extract_die(z));
        }
        System.out.println(a.getWindow().getSlot(2, 3).getDice());
        System.out.println(b.getWindow().getSlot(2, 3).getDice());
    }
}
