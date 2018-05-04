package Game;

import java.util.ArrayList;

public class Round {
    private ArrayList<Turn> turns;

    public Round(Match a) {
        int i=0;
        this.turns =new ArrayList<Turn>(2*a.getnumberPlayers());
        while(i<a.getnumberPlayers()){
            Turn turn = new Turn(a.getPlayers().get(i));
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
}
