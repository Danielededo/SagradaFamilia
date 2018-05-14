package it.polimi.ingsw.Cards.ToolCard;

import java.util.Scanner;
import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.Slot;
import it.polimi.ingsw.Cards.Tool;
import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Dice.Die;
import it.polimi.ingsw.Game.Match;
import it.polimi.ingsw.Game.Player;

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
                    System.out.println("You can't use this ToolCard, you don't have enough Favor Tokens.\n");
                    return false;
                }
            } else {
                if (getPlayer().getMarker() > 1) {
                    getPlayer().setMarker(getPlayer().getMarker() - 2);
                    setUsed(true);
                } else {
                    System.out.println("You can't use this ToolCard, you don't have enough Favor Tokens.\n");
                    return false;
                }
            }
        }

        Colour scelto;
        Die dado1 = new Die();

        //esiste il dado che vuoi prendere
        if(ToolCard12.super.getPlayer().getWindow().getSlot(prima).getDice() == null){
            System.out.println("This slot is empty.\n");
            return false;
        }

        dado1.setFace(ToolCard12.super.getPlayer().getWindow().getSlot(prima).getDice().getFace());
        dado1.setDicecolor(ToolCard12.super.getPlayer().getWindow().getSlot(prima).getDice().getDicecolor());
        scelto = dado1.getDicecolor();
        ArrayList<Die> tracciatoAttuale = partita.getRoundTrack();


        //non siamo al primo turno
        if(tracciatoAttuale.size() == 0){
            System.out.println("You can't use this ToolCard now, there are no dice on the RoundTrack.\n");
            return false;
        }

        //sul tracciato c'è un dado del colore che cerchi
        if(!ToolCard12.this.scorroTrack(tracciatoAttuale, scelto)){
            System.out.println("You can't choose this die.\n");
            return false;
        }

        //rispetto le regole di piazzamento
        if(!partita.getRules().rules(ToolCard12.super.getPlayer(), dopo, dado1)){
            System.out.println("Choose another slot.\n");
            return false;
        }

        //sposto il dado
        ToolCard12.super.getPlayer().getWindow().getSlot(dopo).setDie(dado1);
        ToolCard12.super.getPlayer().getWindow().getSlot(prima).setDie(null);
        return true;
    }



    public boolean effetto12(Match partita, Slot prima1, Slot dopo1, Slot prima2, Slot dopo2){
        if (!isUsed()) {
            if (!this.isAccessed()) {
                if (getPlayer().getMarker() > 0) {
                    getPlayer().setMarker(getPlayer().getMarker() - 1);
                    setUsed(true);
                } else {
                    System.out.println("You can't use this ToolCard, you don't have enough Favor Tokens.\n");
                    return false;
                }
            } else {
                if (getPlayer().getMarker() > 1) {
                    getPlayer().setMarker(getPlayer().getMarker() - 2);
                    setUsed(true);
                } else {
                    System.out.println("You can't use this ToolCard, you don't have enough Favor Tokens.\n");
                    return false;
                }
            }
        }

        Colour scelto;
        Die dado1 = new Die();
        Die dado2 = new Die();

        if(ToolCard12.super.getPlayer().getWindow().getSlot(prima1).getDice() == null){
            System.out.println("This slot is empty.\n");
            return false;
        }

        dado1.setFace(ToolCard12.super.getPlayer().getWindow().getSlot(prima1).getDice().getFace());
        dado1.setDicecolor(ToolCard12.super.getPlayer().getWindow().getSlot(prima1).getDice().getDicecolor());
        scelto = dado1.getDicecolor();
        ArrayList<Die> tracciatoAttuale = partita.getRoundTrack();


        if(tracciatoAttuale.size() == 0){
            System.out.println("You can't use this ToolCard now, there are no dice on the RoundTrack.\n");
            return false;
        }


        if(!ToolCard12.this.scorroTrack(tracciatoAttuale, scelto)){
            System.out.println("You can't choose this die.\n");
            return false;
        }


        if(!partita.getRules().rules(ToolCard12.super.getPlayer(), dopo1, dado1)){
            System.out.println("Choose another slot.\n");
            return false;
        }


        ToolCard12.super.getPlayer().getWindow().getSlot(dopo1).setDie(dado1);
        ToolCard12.super.getPlayer().getWindow().getSlot(prima1).setDie(null);


        //secondo dado
        //esiste
        if(ToolCard12.super.getPlayer().getWindow().getSlot(prima2).getDice() == null){
            System.out.println("This slot is empty");
            ToolCard12.super.getPlayer().getWindow().getSlot(prima1).setDie(dado1);
            ToolCard12.super.getPlayer().getWindow().getSlot(dopo1).setDie(null);
            return false;
        }

        //posso prendere il suo colore
        dado2.setDicecolor(ToolCard12.super.getPlayer().getWindow().getSlot(prima2).getDice().getDicecolor());

        //sono dello stesso colore?
        if(dado2.getDicecolor() != dado1.getDicecolor()){
            System.out.println("You can't choose dice with different colours. Start again.\n");
            ToolCard12.super.getPlayer().getWindow().getSlot(prima1).setDie(dado1);
            ToolCard12.super.getPlayer().getWindow().getSlot(dopo1).setDie(null);
            return false;
        }


        dado2.setFace(ToolCard12.super.getPlayer().getWindow().getSlot(prima2).getDice().getFace());

        //regole di piazzamento
        if(!partita.getRules().rules(ToolCard12.super.getPlayer(),dopo2, dado2)){
            System.out.println("You can't place the selected die on an occupied slot.\n");
            ToolCard12.super.getPlayer().getWindow().getSlot(prima1).setDie(dado1);
            ToolCard12.super.getPlayer().getWindow().getSlot(dopo1).setDie(null);
            return false;
        }

        ToolCard12.super.getPlayer().getWindow().getSlot(dopo2).setDie(dado2);
        ToolCard12.super.getPlayer().getWindow().getSlot(prima2).setDie(null);

        return true;

    }




    public boolean scorroTrack(ArrayList<Die> track, Colour colore){
        int cont = 0;
        while(track.get(cont) != null){
            if(track.get(cont).getDicecolor() == colore){
                return true;
            }
            cont++;
        }
        return false;
    }

}
