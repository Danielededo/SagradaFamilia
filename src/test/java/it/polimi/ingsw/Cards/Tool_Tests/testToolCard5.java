package it.polimi.ingsw.Cards.Tool_Tests;

import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.SchemeCard.KaleidoscopicDream;
import it.polimi.ingsw.Cards.ToolCard.ToolCard5;
import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Dice.Die;
import it.polimi.ingsw.Game.Match;
import it.polimi.ingsw.Game.Player;
import org.junit.jupiter.api.Test;

public class testToolCard5 {
    @Test
    public void testeffect(){
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
        Die z=tool.effect(match.getStock().getDicestock().get(0),c,match);
        match.getStock().show_riserva();
        System.out.println(match.getRoundTrack());
    }
}
