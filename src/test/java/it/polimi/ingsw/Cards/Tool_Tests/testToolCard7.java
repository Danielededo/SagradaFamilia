package it.polimi.ingsw.Cards.Tool_Tests;

import it.polimi.ingsw.Cards.ToolCard.ToolCard5;
import it.polimi.ingsw.Cards.ToolCard.ToolCard7;
import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Dice.Die;
import it.polimi.ingsw.Game.Match;
import it.polimi.ingsw.Game.Player;
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
        tool.effect(match.getStock());
        System.out.println(match.getStock().toString());
    }
}
