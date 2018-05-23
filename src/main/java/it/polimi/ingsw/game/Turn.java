package it.polimi.ingsw.game;

import java.util.Scanner;

public class Turn {
    private Player oneplayer;
    private TurnHandler gestoreTurno = new TurnHandler();




    public Turn(Player player) {
        this.oneplayer=player;
    }



    public void doTurn(Match partita, Round round, int i){
        Scanner sc = new Scanner(System.in);
        int scelta = sc.nextInt();
        switch (scelta){
            case 0: {System.out.println("Il giocatore passa il turno.\n"); break;}
            case 1: {break;}
            case 2: {break;}
            case 3: {break;}

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
