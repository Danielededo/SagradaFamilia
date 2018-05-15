package it.polimi.ingsw.cards.toolCard;

import it.polimi.ingsw.cards.Slot;
import it.polimi.ingsw.cards.Tool;
import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.dice.Die;
import it.polimi.ingsw.game.Match;

import java.util.ArrayList;

public class ToolCard12 extends Tool {

    public ToolCard12() {
        super.setEffect("Muovi fino a due dadi \n " +
                "dello stesso colore di un solo dado \n" +
                "sul Tracciato del Round \n\n" +
                "Devi rispettare tutte le restrizioni di piazzamento ");
        super.setName("Taglierina Manuale");
    }


    public boolean effetto12(Match partita, Slot prima, Slot dopo) {
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


        //esiste il dado che vuoi prendere
        if(prima.getDice() == null){
            System.out.println("This slot is empty.\n");
            return false;
        }

        scelto = prima.getDice().getDicecolor();
        ArrayList<Die> tracciatoAttuale = partita.getRoundTrack();


        //non siamo al primo round
        if(tracciatoAttuale.size() == 0){
            System.out.println("You can't use this toolCard now, there are no dice on the RoundTrack.\n");
            return false;
        }

        //sul tracciato c'è un dado del colore che cerchi
        if(!scorroTrack(tracciatoAttuale, scelto)){
            System.out.println("You can't choose this die.\n");
            return false;
        }

        //rispetto le regole di piazzamento
        if(!partita.getRules().rules(this.getPlayer(), dopo, prima.getDice())){
            System.out.println("Choose another slot.\n");
            return false;
        }

        //sposto il dado
        this.getPlayer().getWindow().getSlot(dopo.getLine(), dopo.getColumn()).setDie(prima.getDice());
        this.getPlayer().getWindow().getSlot(prima.getLine(),prima.getColumn()).setOccupate(false);
        this.getPlayer().getWindow().getSlot(prima.getLine(), prima.getColumn()).setDie(null);
        return true;
    }



    public boolean effetto12(Match partita, Slot prima1,Slot prima2, Slot dopo1, Slot dopo2){
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

        if(this.getPlayer().getWindow().getSlot(prima1).getDice() == null){
            System.out.println("This slot is empty.\n");
            return false;
        }

        scelto = prima1.getDice().getDicecolor();
        ArrayList<Die> tracciatoAttuale = partita.getRoundTrack();


        if(tracciatoAttuale.size() == 0){
            System.out.println("You can't use this toolCard now, there are no dice on the RoundTrack.\n");
            return false;
        }


        if(!this.scorroTrack(tracciatoAttuale, scelto)){
            System.out.println("You can't choose this die.\n");
            return false;
        }


        if(!partita.getRules().rules(ToolCard12.super.getPlayer(), dopo1, prima1.getDice())){
            System.out.println("Choose another slot.\n");
            return false;
        }


        this.getPlayer().getWindow().getSlot(dopo1).setDie(prima1.getDice());
        this.getPlayer().getWindow().getSlot(prima1).setOccupate(false);
        this.getPlayer().getWindow().getSlot(prima1).setDie(null);


        //secondo dado
        //esiste
        if(this.getPlayer().getWindow().getSlot(prima2).getDice() == null){
            System.out.println("This slot is empty");
            this.getPlayer().getWindow().getSlot(prima1).setDie(dopo1.getDice());
            this.getPlayer().getWindow().getSlot(dopo1).setOccupate(false);
            this.getPlayer().getWindow().getSlot(dopo1).setDie(null);
            return false;
        }


        //sono dello stesso colore?
        if(prima2.getDice().getDicecolor() != dopo1.getDice().getDicecolor()){
            System.out.println("You can't choose dice with different colours. Start again.\n");
            this.getPlayer().getWindow().getSlot(prima1).setDie(dopo1.getDice());
            this.getPlayer().getWindow().getSlot(dopo1).setOccupate(false);
            this.getPlayer().getWindow().getSlot(dopo1).setDie(null);
            return false;
        }



        //regole di piazzamento
        if(!partita.getRules().rules(this.getPlayer(),dopo2, prima2.getDice())){
            System.out.println("You can't place the selected die on an occupied slot.\n");
            this.getPlayer().getWindow().getSlot(prima1).setDie(dopo1.getDice());
            this.getPlayer().getWindow().getSlot(dopo1).setOccupate(false);
            this.getPlayer().getWindow().getSlot(dopo1).setDie(null);
            return false;
        }


        this.getPlayer().getWindow().getSlot(dopo2).setDie(prima2.getDice());
        this.getPlayer().getWindow().getSlot(prima2).setOccupate(false);
        this.getPlayer().getWindow().getSlot(prima2).setDie(null);

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
