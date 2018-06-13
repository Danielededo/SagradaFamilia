package it.polimi.ingsw.server.model.cards.tool_Tests;

import it.polimi.ingsw.server.model.cards.schemeCard.SunGlory;
import it.polimi.ingsw.server.model.cards.toolCard.ToolCard12;
import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testToolCard12_Test {

    @Test
    void testingTool12(){
        ToolCard12 carta = new ToolCard12();
        Player a = new Player("a");
        Player b = new Player("b");

        carta.setPlayer(a);
        carta.setUsed(true);
        Match partita = new Match(a,b);
        ArrayList<Die> dice1=new ArrayList<Die>();
        ArrayList<Die> dice2=new ArrayList<Die>();
        //daditrack
        Die uno = new Die(3, Colour.GREEN);
        Die due = new Die(6, Colour.RED);
        Die tre = new Die(2, Colour.PURPLE);
        Die sei = new Die(1, Colour.BLUE);
        dice1.add(uno);
        dice1.add(due);
        dice2.add(tre);
        dice2.add(sei);
        partita.setRound(3);
        partita.setRoundTrack(dice1,0);
        partita.setRoundTrack(dice2,1);
       // System.out.println(partita.getRoundTrackList(0).toString());
        //System.out.println(partita.getRoundTrackList(1).toString());
        System.out.println(partita.toStringRoundTrack());

        carta.setPlayer(a);


        //dadi vetrata
        Die cin = new Die(4, Colour.BLUE);
        Die fin = new Die(6, Colour.RED);
        Die zin = new Die(4, Colour.GREEN);
        Die min = new Die(3, Colour.PURPLE);
        Die nim = new Die(2, Colour.YELLOW);
        Die lim = new Die(6, Colour.PURPLE);
        Die tim = new Die(1, Colour.GREEN);
        a.setWindow(new SunGlory());
        partita.getRules().diePlacing(a,a.getWindow().getSlot(0,0),tim);
        partita.getRules().diePlacing(a,a.getWindow().getSlot(1,0),lim);
        partita.getRules().diePlacing(a,a.getWindow().getSlot(0,1),min);
        partita.getRules().diePlacing(a,a.getWindow().getSlot(0,2),nim);
        partita.getRules().diePlacing(a,a.getWindow().getSlot(1,2),zin);
        partita.getRules().diePlacing(a,a.getWindow().getSlot(0,3),fin);
        partita.getRules().diePlacing(a,a.getWindow().getSlot(0,4),cin);

        System.out.println(a.getWindow().toString());

        //ayo let's go
        carta.effect(null,null,false,partita,null, carta.getPlayer().getWindow().getSlot(1,2), null, carta.getPlayer().getWindow().getSlot(2,1), null,0);

        assertEquals(Colour.GREEN, carta.getPlayer().getWindow().getSlot(2,1).getDice().getDicecolor());
        assertEquals(false, carta.getPlayer().getWindow().getSlot(1,2).isOccupate());
        assertEquals(null, carta.getPlayer().getWindow().getSlot(1,2).getDice());

        System.out.println(a.getWindow().toString());

    }
}
