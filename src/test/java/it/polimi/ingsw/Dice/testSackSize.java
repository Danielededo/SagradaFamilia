package it.polimi.ingsw.Dice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class testSackSize {
    @Test
    void sacksize() {
        Sack s = new Sack();
        System.out.println(s.getsize());
        Assertions.assertEquals(90, s.getsize());
    }
}
