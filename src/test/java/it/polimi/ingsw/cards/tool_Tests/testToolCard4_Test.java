package it.polimi.ingsw.cards.tool_Tests;

import it.polimi.ingsw.cards.GlassWindow;
import it.polimi.ingsw.cards.schemeCard.KaleidoscopicDream;
import it.polimi.ingsw.cards.toolCard.ToolCard4;
import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.dice.Die;
import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Player;
import org.junit.jupiter.api.Test;

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
        if (toolCard4.effect(null,null,false,null,null,a.getWindow().getSlot(1,2),a.getWindow().getSlot(1,0),a.getWindow().getSlot(0,3),a.getWindow().getSlot(2,0),0)){
            System.out.println("operazione riuscita");
        }else System.out.println("operazione fallita");
        System.out.println(a.getWindow().toString());
    }
}
