package it.polimi.ingsw.Cards.Tool_Tests;

import org.junit.jupiter.api.Test;

public class ToolRunner {

    @Test
    public void runThatTool(){
        testToolCard1 toolCard1 = new testToolCard1();
        toolCard1.effectToolCard1();

        testToolCard2 toolCard2 = new testToolCard2();
        toolCard2.effectToolCard2();

        testToolCard3 toolCard3 = new testToolCard3();
        toolCard3.effectToolCard3();

        testToolCard10 toolCard10 = new testToolCard10();
        toolCard10.testingTool10();

        testToolCard9 toolCard9 = new testToolCard9();
        toolCard9.testingTool9();
    }
}
