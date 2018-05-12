package it.polimi.ingsw.Cards.ToolCard;

import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.Slot;
import it.polimi.ingsw.Cards.Tool;
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

    public GlassWindow effetto12(Player giocatore, Match partita, Slot prima, Slot dopo) {
        int i;
        ArrayList<Die> tracciatoAttuale = partita.getRoundTrack();
        for (i=0; i < tracciatoAttuale.size(); i++){ }
        //GlassWindow current = effettoSolitario(giocatore, tracciatoAttuale, prima, dopo);
        return null;
    }


    public GlassWindow effetto12(Player giocatore, Match partita, Slot prima1, Slot dopo1, Slot prima2, Slot dopo2){

        ArrayList<Die> tracciatoAttuale = partita.getRoundTrack();
        //GlassWindow current = effettoDoppio(giocatore,tracciatoAttuale, prima1, dopo1, prima2, dopo2);
        return null;
    }







    //public GlassWindow effettoSolitario(Player giocatore, ArrayList<Die> tracciato, Slot prima, Slot dopo){}



    //public GlassWindow effettoDoppio(Player giocatore, ArrayList<Die> tracciato, Slot prima1, Slot dopo1, Slot prima2, Slot dopo2){}

}
