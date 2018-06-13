package it.polimi.ingsw.server.model.cards.tool_Tests;

import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Player;
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
