package it.polimi.ingsw.server.model.game.rules_Tests;

import it.polimi.ingsw.server.model.cards.GlassWindow;
import it.polimi.ingsw.server.model.cards.schemeCard.Battlo;
import it.polimi.ingsw.server.model.cards.schemeCard.KaleidoscopicDream;
import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Player;
import it.polimi.ingsw.server.model.game.Rules;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MoreTestDefinitive_Test {

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

    @Test
    void test_cont(){
        Player a = new Player("a");
        Player b = new Player("b");
        Rules rules= new Rules();
        Die die=new Die(3,Colour.BLUE);
        GlassWindow g=new Battlo();
        a.setWindow(g);
        rules.diePlacing(a,g.getSlot(0,3),die);
        Assertions.assertEquals(1,rules.getCont(a));
    }
}
