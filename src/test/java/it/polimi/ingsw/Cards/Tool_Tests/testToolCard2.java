package it.polimi.ingsw.Cards.Tool_Tests;

import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.SchemeCard.KaleidoscopicDream;
import it.polimi.ingsw.Cards.ToolCard.ToolCard2;
import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Dice.Die;
import it.polimi.ingsw.Game.Match;
import it.polimi.ingsw.Game.Player;
import it.polimi.ingsw.Game.Rules;
import org.junit.jupiter.api.Test;

public class testToolCard2 {
    @Test
    void effectToolCard2(){
        Player a= new Player("a");
        Player b= new Player("b");
        Match match= new Match(a,b);
        GlassWindow window= new KaleidoscopicDream();
        match.getPlayers().get(0).setWindow(window);
        Die c= new Die(/*4*/5, Colour.RED);  //with 5 is ok, with 4 there is a die with the same value near it
        Die d= new Die(4,Colour.YELLOW);
        Rules rules= new Rules();
        rules.diePlacing(match.getPlayers().get(0),match.getPlayers().get(0).getWindow().getSlot(0,0),d);
        rules.diePlacing(match.getPlayers().get(0),match.getPlayers().get(0).getWindow().getSlot(2,2),c);
        System.out.println(match.getPlayers().get(0).getWindow().toString());
        ToolCard2 tool= new ToolCard2();
        tool.setPlayer(match.getPlayers().get(0));
        tool.getPlayer().setWindow(tool.effect(tool.getPlayer().getWindow().getSlot(2,2),tool.getPlayer().getWindow().getSlot(0,1)));
        System.out.println(match.getPlayers().get(0).getWindow().toString());
    }
}
