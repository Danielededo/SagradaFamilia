package it.polimi.ingsw.server.model.cards.tool_Tests;

import it.polimi.ingsw.server.model.cards.toolCard.ToolCard5;
import it.polimi.ingsw.utils.Colour;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        ArrayList<Die> dice=new ArrayList<Die>();
        dice.add(c);
        dice.add(d);
        match.setRoundTrack(dice,0);
        match.getStock().setDicestock(match.getSack().extractfromSack(match));
        System.out.println(match.getStock().toString());
        ArrayList<Die> dice1=new ArrayList<>();
        dice1.add(match.getStock().getDicestock().get(0));
        dice1.add(c);
        if (tool.effect(dice1,match,null,0)){
            System.out.println("operzione riuscita");
        }else System.out.println("operazione fallita");
        match.getStock().show_stock();
        System.out.println(match.getRoundTrack());
    }
}
