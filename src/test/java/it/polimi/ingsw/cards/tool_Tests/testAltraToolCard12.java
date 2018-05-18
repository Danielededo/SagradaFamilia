package it.polimi.ingsw.cards.tool_Tests;

import it.polimi.ingsw.cards.schemeCard.SunGlory;
import it.polimi.ingsw.cards.toolCard.ToolCard12;
import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.dice.Die;
import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testAltraToolCard12 {

    @Test
    void testAgain(){
        ToolCard12 carta = new ToolCard12();
        Player a = new Player("a");
        Player b = new Player("b");

        carta.setPlayer(a);
        carta.setUsed(true);
        Match partita = new Match(a,b);
        ArrayList<Die> track = new ArrayList<Die>();
        //daditrack
        Die uno = new Die(3, Colour.GREEN);
        Die due = new Die(6, Colour.RED);
        Die tre = new Die(2, Colour.PURPLE);
        Die sei = new Die(1, Colour.BLUE);
        track.add(uno); track.add(due); track.add(tre); track.add(sei);
        partita.setRoundTrack(track);
        System.out.println(partita.getRoundTrack());


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


        carta.effect(null,null,false,partita,null, carta.getPlayer().getWindow().getSlot(0,1),carta.getPlayer().getWindow().getSlot(1,0), carta.getPlayer().getWindow().getSlot(1,3), carta.getPlayer().getWindow().getSlot(2,1),0);
        assertEquals(Colour.PURPLE, carta.getPlayer().getWindow().getSlot(2,1).getDice().getDicecolor());
        assertEquals(null, carta.getPlayer().getWindow().getSlot(1,0).getDice());




        System.out.println(carta.getPlayer().getWindow().toString());
    }
}
