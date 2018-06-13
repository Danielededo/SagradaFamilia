package it.polimi.ingsw.server.model.game;

public class Turn {
    private Player oneplayer;

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
