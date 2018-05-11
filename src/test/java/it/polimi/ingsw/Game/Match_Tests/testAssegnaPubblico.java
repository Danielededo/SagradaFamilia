package it.polimi.ingsw.Game.Match_Tests;

import it.polimi.ingsw.Game.Match;
import it.polimi.ingsw.Game.Player;
import org.junit.jupiter.api.Test;

public class testAssegnaPubblico {
    @Test
    void assegnamentoObiettivoPubPriv(){
        Player a= new Player("mario");
        Player b= new Player("sara");
        Match game = new Match(a,b);
        game.setPublictarget();
        game.setPrivateObject();
        System.out.println(game.toString());
    }
}
