package it.polimi.ingsw;

import it.polimi.ingsw.Cards.CardsRunner;
import it.polimi.ingsw.Dice.DiceRunner;
import it.polimi.ingsw.Game.GameRunner;
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
