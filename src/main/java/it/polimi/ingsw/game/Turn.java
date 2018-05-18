package it.polimi.ingsw.game;

import java.util.Scanner;

public class Turn {
    private Player oneplayer;
    private TurnHandler gestoreTurno = new TurnHandler();




    public Turn(Player player) {
        this.oneplayer=player;
    }



    public void doTurn(Match partita, Round round, int i){
        System.out.println("Choose what to do during your turn: \n0: saltare il turno; \n1: piazzare un dado della riserva; \n2: usare una carta tool; \n3: piaare un dado e unsare una carta tool;");
        Scanner sc = new Scanner(System.in);
        int scelta = sc.nextInt();
        switch (scelta){
            case 0: {System.out.println("Il giocatore passa il turno.\n"); break;}
            case 1: {}
            case 2: {}
            case 3: {}
        }
    }

    public TurnHandler getGestoreTurno() {
        return gestoreTurno;
    }

    public Player getOneplayer() {
        return oneplayer;
    }



    @Override
    public String toString() {

        return "Turn{" +
                "oneplayer=" + oneplayer +
                '}';
    }

}
