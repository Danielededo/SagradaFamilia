package it.polimi.ingsw.game.rules_Tests;

import it.polimi.ingsw.cards.GlassWindow;
import it.polimi.ingsw.cards.schemeCard.Battlo;
import it.polimi.ingsw.cards.schemeCard.KaleidoscopicDream;
import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.dice.Die;
import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Player;
import it.polimi.ingsw.game.Rules;
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
