package Game;

public class Turn {
    private Player oneplayer;

    public Turn(Player player) {
        this.oneplayer=player;
    }

    public void takeDie(Stock stock,int a){
        stock.extract_die(a);
    }

    @Override
    public String toString() {
        return "Turn{" +
                "oneplayer=" + oneplayer +
                '}';
    }
}
