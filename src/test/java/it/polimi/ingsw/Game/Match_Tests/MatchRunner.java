package it.polimi.ingsw.Game.Match_Tests;

import org.junit.jupiter.api.Test;

public class MatchRunner {
    @Test
    public void runThisMatch(){
        RuotaTurni2 ruota = new RuotaTurni2();
        ruota.rotateturn2Players();

        testAssegnaPubblico pubblico = new testAssegnaPubblico();
        pubblico.assegnamentoObiettivoPubPriv();

        testProvaPartita prova = new testProvaPartita();
        prova.partitaprova();

        testStampa stampa = new testStampa();
        stampa.print();
    }
}
