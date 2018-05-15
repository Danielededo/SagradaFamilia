package it.polimi.ingsw.Game;

import it.polimi.ingsw.Game.Match_Tests.MatchRunner;
import it.polimi.ingsw.Game.Rules_Tests.Rules_Tests;
import org.junit.jupiter.api.Test;

public class GameRunner {
    @Test
    public void runThisGame(){
        MatchRunner match = new MatchRunner();
        match.runThisMatch();

        Rules_Tests regole = new Rules_Tests();
        regole.runRules();
    }
}
