package it.polimi.ingsw.Cards.Tool_Tests;

import it.polimi.ingsw.Cards.SchemeCard.SunGlory;
import it.polimi.ingsw.Cards.ToolCard.ToolCard12;
import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Dice.Die;
import it.polimi.ingsw.Game.Match;
import it.polimi.ingsw.Game.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class testToolCard12 {

    @Test
    void testingTool12(){
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


        //ayo let's go
        carta.effetto12(partita, carta.getPlayer().getWindow().getSlot(1,2), carta.getPlayer().getWindow().getSlot(2,1));

        assertEquals(Colour.GREEN, carta.getPlayer().getWindow().getSlot(2,1).getDice().getDicecolor());
        assertEquals(false, carta.getPlayer().getWindow().getSlot(1,2).isOccupate());
        assertEquals(null, carta.getPlayer().getWindow().getSlot(1,2).getDice());

        System.out.println(a.getWindow().toString());

    }
}
