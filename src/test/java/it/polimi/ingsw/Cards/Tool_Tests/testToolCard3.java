package it.polimi.ingsw.Cards.Tool_Tests;

import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.SchemeCard.KaleidoscopicDream;
import it.polimi.ingsw.Cards.ToolCard.ToolCard3;
import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Dice.Die;
import it.polimi.ingsw.Game.Match;
import it.polimi.ingsw.Game.Player;
import it.polimi.ingsw.Game.Rules;
import org.junit.jupiter.api.Test;

public class testToolCard3 {
    @Test
    void effectToolCard3(){
        Player a= new Player("a");
        Player b= new Player("b");
        Match match= new Match(a,b);
        GlassWindow window= new KaleidoscopicDream();
        match.getPlayers().get(0).setWindow(window);
        Die c= new Die(2, Colour.RED/*Colour.YELLOW */);  //
        Die d= new Die(1,Colour.YELLOW);
        Rules rules= new Rules();
        rules.diePlacing(match.getPlayers().get(0),match.getPlayers().get(0).getWindow().getSlot(0,4),d);
        rules.diePlacing(match.getPlayers().get(0),match.getPlayers().get(0).getWindow().getSlot(3,0),c);
        System.out.println(match.getPlayers().get(0).getWindow().toString());
        ToolCard3 tool= new ToolCard3();
        tool.setPlayer(match.getPlayers().get(0));
        if (tool.effect(tool.getPlayer().getWindow().getSlot(3,0),tool.getPlayer().getWindow().getSlot(1,4))){
            System.out.println("operazione riuscita");
        }else System.out.println("operazione fallita");
        System.out.println(match.getPlayers().get(0).getWindow().toString());
    }
}
