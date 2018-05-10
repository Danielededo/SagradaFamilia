package it.polimi.ingsw.Game;

import java.util.Scanner;

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

    public void Hand(){
        int j=2;
        while(j!=0){
            System.out.println("1. if you want to set a Die from the stock to your glasswindow ");
            System.out.println("2. if you want to use a tool card ");
            Scanner in = new Scanner(System.in);
            int i = in.nextInt();
            switch (i) {
                case 1: {

                    break;
                }
                case 2: {

                    break;
                }
            }
        }
    }
}
