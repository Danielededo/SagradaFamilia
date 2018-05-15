package it.polimi.ingsw.game.match_Tests;

import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Player;
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
