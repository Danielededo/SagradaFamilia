package it.polimi.ingsw.cards.glasswindow_Tests;

import org.junit.jupiter.api.Test;

public class GlassWindowRunner {

        @Test
        public void runGlassWindow(){
            testCreateWindow creazione = new testCreateWindow();
            creazione.testCreateWindow();

            testInserimento inserisco = new testInserimento();
            inserisco.testWindow();

            testRiempimentoWindow riempio = new testRiempimentoWindow();
            riempio.testRiempimento();
        }
    }
