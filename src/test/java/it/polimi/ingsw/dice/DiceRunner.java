package it.polimi.ingsw.dice;

import org.junit.jupiter.api.Test;

public class DiceRunner {
    @Test
    public void runDice(){
        Die_Tests dado = new Die_Tests();
        dado.setDice();

        testSackRemove rimozione = new testSackRemove();
        rimozione.sackremove();

        testSackSize size = new testSackSize();
        size.sacksize();
    }
}