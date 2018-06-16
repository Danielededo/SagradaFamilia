package it.polimi.ingsw.server.model.game;

import java.util.ArrayList;

public class Round {
    private ArrayList<Turn> turns;
    private Stock stock= new Stock();


    public Stock getStock() {
        return stock;
    }

    public Round(Match a) {
        int i = 0;
        a.getStock().setDicestock(a.getSack().extractfromSack(a));
        this.turns =new ArrayList<Turn>(2*a.getnumberPlayers());
        while(i<a.getnumberPlayers()){
            Turn turn = new Turn(a.getPlayers().get(i));
            turn.getOneplayer().setContTurn(1);
            this.turns.add(turn);
            i++;
        }
        i--;
        while(i>=0){
            Turn turn = new Turn(a.getPlayers().get(i));
            this.turns.add(turn);
            i--;
        }
    }

    public ArrayList<Turn> getTurns() {
        return turns;
    }

    public void clearRound(){
        this.turns.clear();
    }

    @Override
    public String toString() {
        return "Round{" +
                "turni=" + turns +
                '}';
    }
}
