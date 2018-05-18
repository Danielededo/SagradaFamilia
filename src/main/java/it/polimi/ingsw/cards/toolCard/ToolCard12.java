package it.polimi.ingsw.cards.toolCard;

import it.polimi.ingsw.cards.Slot;
import it.polimi.ingsw.cards.Tool;
import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.dice.Die;
import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Stock;

import java.util.ArrayList;

public class ToolCard12 extends Tool {

    public ToolCard12() {
        super.setEffect("Muovi fino a due dadi \n " +
                "dello stesso colore di un solo dado \n" +
                "sul Tracciato del Round \n\n" +
                "Devi rispettare tutte le restrizioni di piazzamento ");
        super.setName("Taglierina Manuale");
        super.setValue(12);
    }


    public boolean effect(Die dado1, Die dado2, boolean piumeno, Match partita, Stock stock, Slot slot1, Slot slot2, Slot slot3, Slot slot4, int value){
        if (!isUsed()) {
            if (!this.isAccessed()) {
                if (getPlayer().getMarker() > 0) {
                    getPlayer().setMarker(getPlayer().getMarker() - 1);
                    setUsed(true);
                } else {
                    System.out.println("You can't use this toolCard, you don't have enough Favor Tokens.\n");
                    return false;
                }
            } else {
                if (getPlayer().getMarker() > 1) {
                    getPlayer().setMarker(getPlayer().getMarker() - 2);
                    setUsed(true);
                } else {
                    System.out.println("You can't use this toolCard, you don't have enough Favor Tokens.\n");
                    return false;
                }
            }
        }

        Colour scelto;

        if(this.getPlayer().getWindow().getSlot(slot1).getDice() == null){
            System.out.println("This slot is empty.\n");
            return false;
        }

        scelto = slot1.getDice().getDicecolor();
        ArrayList<Die> tracciatoAttuale = partita.getRoundTrack();


        if(tracciatoAttuale.size() == 0){
            System.out.println("You can't use this toolCard now, there are no dice on the RoundTrack.\n");
            return false;
        }


        if(!this.scorroTrack(tracciatoAttuale, scelto)){
            System.out.println("You can't choose this die.\n");
            return false;
        }


        if(!partita.getRules().rules(ToolCard12.super.getPlayer(), slot3, slot1.getDice())){
            System.out.println("Choose another slot.\n");
            return false;
        }


        this.getPlayer().getWindow().getSlot(slot3).setDie(slot1.getDice());
        this.getPlayer().getWindow().getSlot(slot1).setOccupate(false);
        this.getPlayer().getWindow().getSlot(slot1).setDie(null);


        if(slot2 == null){
            return true;
        }

        //secondo dado
        //esiste
        if(this.getPlayer().getWindow().getSlot(slot2).getDice() == null){
            System.out.println("This slot is empty");
            this.getPlayer().getWindow().getSlot(slot1).setDie(slot3.getDice());
            this.getPlayer().getWindow().getSlot(slot3).setOccupate(false);
            this.getPlayer().getWindow().getSlot(slot3).setDie(null);
            return false;
        }


        //sono dello stesso colore?
        if(slot2.getDice().getDicecolor() != slot3.getDice().getDicecolor()){
            System.out.println("You can't choose dice with different colours. Start again.\n");
            this.getPlayer().getWindow().getSlot(slot1).setDie(slot3.getDice());
            this.getPlayer().getWindow().getSlot(slot3).setOccupate(false);
            this.getPlayer().getWindow().getSlot(slot3).setDie(null);
            return false;
        }



        //regole di piazzamento
        if(!partita.getRules().rules(this.getPlayer(),slot4, slot2.getDice())){
            System.out.println("You can't place the selected die on an occupied slot.\n");
            this.getPlayer().getWindow().getSlot(slot1).setDie(slot3.getDice());
            this.getPlayer().getWindow().getSlot(slot3).setOccupate(false);
            this.getPlayer().getWindow().getSlot(slot3).setDie(null);
            return false;
        }


        this.getPlayer().getWindow().getSlot(slot4).setDie(slot2.getDice());
        this.getPlayer().getWindow().getSlot(slot2).setOccupate(false);
        this.getPlayer().getWindow().getSlot(slot2).setDie(null);

        return true;

    }




    public boolean scorroTrack(ArrayList<Die> track, Colour colore){
        int cont;
        for(cont = 0; cont < track.size(); cont++){
            if(track.get(cont).getDicecolor() == colore){
                return true;
            }
        }
        return false;
    }

}
