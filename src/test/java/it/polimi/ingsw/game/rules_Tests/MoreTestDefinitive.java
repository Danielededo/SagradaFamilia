package it.polimi.ingsw.game.rules_Tests;

import it.polimi.ingsw.cards.GlassWindow;
import it.polimi.ingsw.cards.schemeCard.KaleidoscopicDream;
import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Player;
import it.polimi.ingsw.game.Rules;
import org.junit.jupiter.api.Test;

public class MoreTestDefinitive {

    @Test
    void AncoraTestDefinitive() {
        Player a = new Player("a");
        Player b = new Player("b");
        Player c = new Player("c");
        Match partita = new Match(a,b,c);
        partita.setRound(8);

        Rules r = new Rules();
        GlassWindow windowA = new KaleidoscopicDream();


    }
}
