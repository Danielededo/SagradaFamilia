package it.polimi.ingsw;

import it.polimi.ingsw.cards.CardsRunner;
import it.polimi.ingsw.dice.DiceRunner;
import it.polimi.ingsw.game.GameRunner;
import org.junit.jupiter.api.Test;

public class Sagrada_Test {
    @Test
    public void mainRunner(){
        CardsRunner carte = new CardsRunner();
        carte.runAllCards();

        DiceRunner dadi = new DiceRunner();
        dadi.runDice();

        GameRunner gioco = new GameRunner();
        gioco.runThisGame();

    }
}
