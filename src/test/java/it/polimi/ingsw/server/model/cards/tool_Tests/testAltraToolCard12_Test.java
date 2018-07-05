package it.polimi.ingsw.server.model.cards.tool_Tests;

import it.polimi.ingsw.server.model.cards.Slot;
import it.polimi.ingsw.server.model.cards.schemeCard.SunGlory;
import it.polimi.ingsw.server.model.cards.toolCard.ToolCard12;
import it.polimi.ingsw.utils.Colour;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testAltraToolCard12_Test {

    @Test
    void testAgain(){
        ToolCard12 carta = new ToolCard12();
        Player a = new Player("a");
        Player b = new Player("b");

        carta.setPlayer(a);
        Match partita = new Match(a,b);
        ArrayList<Die> track = new ArrayList<Die>();
        //daditrack
        Die uno = new Die(3, Colour.GREEN);
        Die due = new Die(6, Colour.RED);
        Die tre = new Die(2, Colour.PURPLE);
        Die sei = new Die(1, Colour.BLUE);
        track.add(uno); track.add(due); track.add(tre); track.add(sei);
        partita.setRound(2);
        partita.setRoundTrack(track,0);
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

        System.out.println(a.getWindow().toString());
        ArrayList<Slot> slots=new ArrayList<>();
        slots.add(carta.getPlayer().getWindow().getSlot(0,1));
        slots.add(carta.getPlayer().getWindow().getSlot(1,3));
        slots.add(carta.getPlayer().getWindow().getSlot(1,0));
        slots.add(carta.getPlayer().getWindow().getSlot(2,1));

        carta.effect(null,partita,slots,0);
        System.out.println(a.getWindow().toString());
        assertEquals(Colour.PURPLE, carta.getPlayer().getWindow().getSlot(2,1).getDice().getDicecolor());
        assertEquals(null, carta.getPlayer().getWindow().getSlot(1,0).getDice());




        System.out.println(carta.getPlayer().getWindow().toString());
    }
}
