package it.polimi.ingsw.cards.tool_Tests;

import it.polimi.ingsw.cards.toolCard.ToolCard5;
import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.dice.Die;
import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Player;
import org.junit.jupiter.api.Test;

public class testToolCard5_Test {
    @Test
    public void effectToolCard5(){
        Player a= new Player("a");
        Player b= new Player("b");
        Match match= new Match(a,b);
        ToolCard5 tool=new ToolCard5();
        tool.setPlayer(a);
        Die c= new Die(2, Colour.RED/*Colour.YELLOW */);  //
        Die d= new Die(1,Colour.YELLOW);
        match.getRoundTrack().add(c);
        match.getStock().setDicestock(match.getSack().extractfromSack(match));
        System.out.println(match.getStock().toString());
        if (tool.effect(match.getStock().getDicestock().get(0),c,false,match, null,null,null,null,null,0)){
            System.out.println("operzione riuscita");
        }else System.out.println("operazione fallita");
        match.getStock().show_riserva();
        System.out.println(match.getRoundTrack());
    }
}
