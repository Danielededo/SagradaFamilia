package it.polimi.ingsw.dice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class testSackSize_Test {
    @Test
    void sacksize() {
        Sack s = new Sack();
        System.out.println(s.getsize());
        Assertions.assertEquals(90, s.getsize());
    }
}
