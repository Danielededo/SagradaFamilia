package it.polimi.ingsw.cards.publicObject_Tests;

import org.junit.jupiter.api.Test;

public class PublicObjectRunner {
    @Test
    public void runPublic(){
        testClearShades clear = new testClearShades();
        clear.test_clear_shades();

        testColoriDiagonali diagonali = new testColoriDiagonali();
        diagonali.testa_colored_diagonals();

        testDiversiColoriColonna colocolo = new testDiversiColoriColonna();
        colocolo.testa_different_columnColors();

        testDiversiColoriRiga coloriga = new testDiversiColoriRiga();
        coloriga.testa_different_rowColors();

        testDiversiValoriColonna valocolo = new testDiversiValoriColonna();
        valocolo.testadifferent_columnShades();

        testDiversiValoriRiga valoriga = new testDiversiValoriRiga();
        valoriga.testadifferent_rowShades();
    }
}
