package it.polimi.ingsw.cards.tool_Tests;

import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Player;
import org.junit.jupiter.api.Test;

public class ExtractTool_Test {
    @Test
    void extractToolCards(){
        Player a=new Player("nick");
        Player b=new Player("antonio");
        Match match= new Match(a,b);
        System.out.println(match.getTool().toString());
    }
}
