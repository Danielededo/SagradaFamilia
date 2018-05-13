package it.polimi.ingsw.Game.Match_Tests;

import it.polimi.ingsw.Dice.Sack;
import it.polimi.ingsw.Game.Match;
import it.polimi.ingsw.Game.Player;
import it.polimi.ingsw.Game.Round;
import org.junit.jupiter.api.Test;

public class testProvaPartita {

    @Test
    void partitaprova() {
        Player a = new Player("Daniele");
        Player b = new Player("Sara");
        Match match = new Match(a, b);
        Sack sack=new Sack();
        /*Round round = new Round(match);
        match.getPlayers().get(0).setWindow(match.getScheme().extractGlass());
        match.getPlayers().get(1).setWindow(match.getScheme().extractGlass());*/
        System.out.println(match.getSack().extractfromSack(match).toString());
        System.out.println(sack.toString());
    }
}
