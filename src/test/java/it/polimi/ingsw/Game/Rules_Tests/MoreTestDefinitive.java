package it.polimi.ingsw.Game.Rules_Tests;

import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.SchemeCard.Gravitas;
import it.polimi.ingsw.Cards.SchemeCard.KaleidoscopicDream;
import it.polimi.ingsw.Game.Match;
import it.polimi.ingsw.Game.Player;
import it.polimi.ingsw.Game.Rules;
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
