package it.polimi.ingsw.server.model.cards.tool_Tests;

import it.polimi.ingsw.server.model.cards.GlassWindow;
import it.polimi.ingsw.server.model.cards.schemeCard.KaleidoscopicDream;
import it.polimi.ingsw.server.model.cards.toolCard.ToolCard2;
import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Player;
import it.polimi.ingsw.server.model.game.Rules;
import org.junit.jupiter.api.Test;

public class testToolCard2_Test {
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
        if (tool.effect(null,null,false,null,null,tool.getPlayer().getWindow().getSlot(2,2),tool.getPlayer().getWindow().getSlot(0,1),null,null,0)){
            System.out.println("operazione riuscita");
        }else System.out.println("operazione fallita");
        System.out.println(match.getPlayers().get(0).getWindow().toString());
    }
}
