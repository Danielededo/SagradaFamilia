package it.polimi.ingsw.server.model.cards.tool_Tests;

import it.polimi.ingsw.server.model.cards.GlassWindow;
import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.schemeCard.KaleidoscopicDream;
import it.polimi.ingsw.server.model.cards.toolCard.ToolCard3;
import it.polimi.ingsw.utils.Colour;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Player;
import it.polimi.ingsw.server.model.game.Rules;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class testToolCard3_Test {
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
        ArrayList<Slot> slots=new ArrayList<>();
        slots.add(tool.getPlayer().getWindow().getSlot(3,0));
        slots.add(tool.getPlayer().getWindow().getSlot(1,4));
        if (tool.effect(null,null,slots,0)){
            System.out.println("operazione riuscita");
        }else System.out.println("operazione fallita");
        System.out.println(match.getPlayers().get(0).getWindow().toString());
    }
}
