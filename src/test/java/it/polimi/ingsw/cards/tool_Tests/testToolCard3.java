package it.polimi.ingsw.cards.tool_Tests;

import it.polimi.ingsw.cards.GlassWindow;
import it.polimi.ingsw.cards.schemeCard.KaleidoscopicDream;
import it.polimi.ingsw.cards.toolCard.ToolCard3;
import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.dice.Die;
import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Player;
import it.polimi.ingsw.game.Rules;
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
        if (tool.effect(null,null,false, null,null, tool.getPlayer().getWindow().getSlot(3,0),tool.getPlayer().getWindow().getSlot(1,4),null,null,0)){
            System.out.println("operazione riuscita");
        }else System.out.println("operazione fallita");
        System.out.println(match.getPlayers().get(0).getWindow().toString());
    }
}
