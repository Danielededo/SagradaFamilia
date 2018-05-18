package it.polimi.ingsw.cards.tool_Tests;

import it.polimi.ingsw.cards.toolCard.ToolCard7;
import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Player;
import org.junit.jupiter.api.Test;

public class testToolCard7 {
    @Test
    public void effectToolCard7(){
        Player a= new Player("a");
        Player b= new Player("b");
        Match match= new Match(a,b);
        ToolCard7 tool=new ToolCard7();
        tool.setPlayer(a);
        match.getStock().setDicestock(match.getSack().extractfromSack(match));
        System.out.println(match.getStock().toString());
        a.setContTurn(2);
        tool.effect(null,null,false,null,match.getStock(),null,null,null,null,0);
        System.out.println(match.getStock().toString());
    }
}
