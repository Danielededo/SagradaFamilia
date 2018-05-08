package it.polimi.ingsw.Dice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sack_Test {
    @Test
    void sacksize() {
        Sack s = new Sack();
        System.out.println(s.getsize());
        Assertions.assertEquals(90, s.getsize());
    }

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
