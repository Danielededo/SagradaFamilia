package it.polimi.ingsw.Dice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class testSackRemove {
    @Test
    void sackremove() {
        Sack s = new Sack();
        Die d = new Die();
        for (int i = 0; i < 90; i++) {
            d = s.extractdie();
        }
        Assertions.assertEquals(0, s.getsize());
    }
}