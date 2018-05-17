package it.polimi.ingsw.game;

public class Turn {
    private Player oneplayer;
    private TurnHandler gestoreTurno = new TurnHandler();



    public Turn(Player player) {
        this.oneplayer=player;
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
