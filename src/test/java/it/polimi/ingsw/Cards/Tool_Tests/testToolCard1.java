package it.polimi.ingsw.Cards.Tool_Tests;

import it.polimi.ingsw.Cards.ToolCard.ToolCard1;
import it.polimi.ingsw.Dice.Die;
import it.polimi.ingsw.Game.Match;
import it.polimi.ingsw.Game.Player;
import org.junit.jupiter.api.Test;

public class testToolCard1 {
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
        Die die=tool.effect(match.getStock().getDicestock().get(0),false);
        System.out.println(die.toString());
        System.out.println(match.getStock().toString());
        System.out.println(match.getPlayers().get(0).toString());
    }
}
