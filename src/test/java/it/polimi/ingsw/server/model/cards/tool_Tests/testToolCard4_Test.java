package it.polimi.ingsw.server.model.cards.tool_Tests;

import it.polimi.ingsw.server.model.cards.GlassWindow;
import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.schemeCard.KaleidoscopicDream;
import it.polimi.ingsw.server.model.cards.toolCard.ToolCard4;
import it.polimi.ingsw.utils.Colour;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class testToolCard4_Test {
    @Test
    void effectToolCard4(){
        Player a= new Player("a");
        Player b= new Player("b");
        Match match= new Match(a,b);
        GlassWindow window= new KaleidoscopicDream();
        a.setWindow(window);
        Die c= new Die(5, Colour.GREEN );
        Die d= new Die(3,Colour.GREEN);
        ToolCard4 toolCard4= new ToolCard4();
        toolCard4.setPlayer(a);
        a.getWindow().getSlot(1,2).setDie(c);
        a.getWindow().getSlot(0,3).setDie(d);
        System.out.println(match.getPlayers().get(0).getWindow().toString());
        ArrayList<Slot> slots=new ArrayList<>();
        slots.add(a.getWindow().getSlot(1,2));
        slots.add(a.getWindow().getSlot(1,0));
        slots.add(a.getWindow().getSlot(0,3));
        slots.add(a.getWindow().getSlot(2,0));
        if (toolCard4.effect(null,null,slots,0)){
            System.out.println("operazione riuscita");
        }else System.out.println("operazione fallita");
        System.out.println(a.getWindow().toString());
    }
}
