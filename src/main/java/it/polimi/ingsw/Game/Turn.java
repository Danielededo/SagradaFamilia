package it.polimi.ingsw.Game;

import it.polimi.ingsw.Dice.Die;

public class Turn {
    private Player oneplayer;

    public Turn(Player player) {
        this.oneplayer=player;
    }

    public Player getOneplayer() {
        return oneplayer;
    }

    public void takeDie(Stock stock, int a){
        stock.extract_die(a);
    }

    @Override
    public String toString() {
        return "Turn{" +
                "oneplayer=" + oneplayer +
                '}';
    }

    public void Hand(Match match){
        int j=1;
        int i=4;
        while(j!=0){
            System.out.println("this is your glass "+ oneplayer.getWindow());
            System.out.println("press 1 if you want to select a Die and place it in your glass");
            System.out.println("press 2 if you want to select a ToolCard from the table");
            System.out.println("press 3 if you want to end turn");
            switch (i) {
                case 1: {
                    Die a;
                    a= match.getStock().extract_die(0);
                    oneplayer.setWindow(match.getRules().diePlacing(match,oneplayer,oneplayer.getWindow().getSlot(2,4),a));
                    break;
                }
                case 2: {

                    break;
                }
                case 3:{

                    break;
                }
            }
        }
    }
}
