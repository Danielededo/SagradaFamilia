package it.polimi.ingsw.server.model.cards.tool_Tests;

import it.polimi.ingsw.server.model.cards.toolCard.ToolCard1;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class testToolCard1_Test {
    @Test
    void effectToolCard1(){
        Player a= new Player("mario");
        Player b= new Player("daniele");
        Match match= new Match(a,b);
        ToolCard1 tool= new ToolCard1();
        match.setPlayerswindow();
        System.out.println(a.toString());
        System.out.println(b.toString());
        match.getStock().setDicestock(match.getSack().extractfromSack(match));
        tool.setPlayer(match.getPlayers().get(0));
        System.out.println(match.getStock().toString());
        ArrayList<Die> dice=new ArrayList<>();
        dice.add(match.getStock().getDicestock().get(0));
        if (tool.effect(dice,null,null,0)){
            System.out.println("operazione eseguita");
        }else System.out.println("operazione fallita");
        System.out.println(match.getStock().toString());
        System.out.println(match.getPlayers().get(0).toString());
    }
}
