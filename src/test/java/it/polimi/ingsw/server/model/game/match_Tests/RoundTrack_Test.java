package it.polimi.ingsw.server.model.game.match_Tests;

import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class RoundTrack_Test{
    @Test
    void roundTrack(){
        Player a=new Player("daniele");
        Player b=new Player("mario");
        Match match=new Match(a,b);
        match.setRound(3); //the 3rd round means that roundTrack [0] and [1] exist
        match.getStock().setDicestock(match.getSack().extractfromSack(match));
        ArrayList<Die> die=new ArrayList<Die>(),ar=new ArrayList<Die>();
        die.addAll(match.getStock().getDicestock());
        match.setRoundTrack(die,0);
        match.getStock().reset_stock();
        match.getStock().setDicestock(match.getSack().extractfromSack(match));
        ar.addAll(match.getStock().getDicestock());
        match.setRoundTrack(ar,1);
        System.out.println(match.toStringRoundTrack());
    }
}
