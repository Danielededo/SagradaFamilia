package it.polimi.ingsw.game;

import it.polimi.ingsw.dice.Die;

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

    public void Hand(Match match) {
        if (!oneplayer.isMissednext_turn()) {
            int j = 1;
            String i;
            while (j != 0) {
                System.out.println(oneplayer.getNickname() + " this is your glass " + oneplayer.getWindow());
                System.out.println("digit:  >place a die<        if you want to select a Die and place it in your glass");
                System.out.println("digit:  >use a toolcard<     if you want to select a toolCard from the table");
                System.out.println("digit:  >end<                if you want to end turn");
                System.out.println("digit without > < character");
                Scanner in = new Scanner(System.in);
                i = in.nextLine();
                if ("place a die".equals(i)) {
                    Die a;
                    System.out.println("Select a die from stock by selecting its index->");
                    match.getStock().show_riserva();
                    Scanner index = new Scanner(System.in);
                    int k = index.nextInt();
                    if (k >= 0 && k < match.getStock().getDicestock().size()) {
                        a = match.getStock().extract_die(k);
                        Scanner slot_x = new Scanner(System.in);
                        Scanner slot_y = new Scanner(System.in);
                        int slotindex_x = -1;
                        int slotindex_y = -1;
                        while ((slotindex_x < 0 || slotindex_x > 3) || (slotindex_y < 0 || slotindex_y > 4)) {
                            System.out.println("Now select the slot where you want to put your die by selecting its line and column in order respectively->");
                            System.out.print("line->");
                            slotindex_x = slot_x.nextInt();
                            System.out.print("column->");
                            slotindex_y = slot_y.nextInt();
                        }
                        oneplayer.setWindow(match.getRules().diePlacing(oneplayer, oneplayer.getWindow().getSlot(slotindex_x, slotindex_y), a));
                        if (!getOneplayer().getWindow().getSlot(slotindex_x, slotindex_y).isOccupate()) {
                            System.out.println("The die chosen couldn't be placed in the selected slot");
                            match.getStock().getDicestock().add(a);
                        }
                    }
                } else if ("use a toolcard".equals(i)) {

                } else if ("end".equals(i)) {
                    System.out.println("End turn -> next Player");
                    j = 0;
                }
            }
        }else System.out.println(oneplayer.getNickname()+" jump this turn by effect of the card chosen in previously turn");
    }
}
