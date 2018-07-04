package it.polimi.ingsw.server.model.cards.publicCard;

import it.polimi.ingsw.server.model.cards.PublicObject;
import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.model.game.Player;

import java.util.ArrayList;

public class DifferentRowColors extends PublicObject {
    public DifferentRowColors() {
        setName("Colori diversi - Riga");
        setValue(1);
        setPunteggio(6);
        setEffect("Righe senza colori ripetuti");
    }

    /**
     * Calculates number of row without repeated colour
     * @param player need its scheme card
     * @return int cont number of row
     */
    private int numberrow(Player player) {
        int cont=0;
        ArrayList<Colour> colori = new ArrayList<Colour>();
        for (int i=0;i<width;i++){
            colori.add(Colour.RED);
            colori.add(Colour.BLUE);
            colori.add(Colour.YELLOW);
            colori.add(Colour.GREEN);
            colori.add(Colour.PURPLE);
            for (int j=0;j<height;j++){
                if (player.getWindow().getSlot(i,j).isOccupate() &&
                        colori.contains(player.getWindow().getSlot(i,j).getDice().getDicecolor())){
                    colori.remove(player.getWindow().getSlot(i,j).getDice().getDicecolor());
                }
            }
            if (colori.size()==0) {
                cont++;
            }
            colori.clear();
        }
        return cont;
    }

    /**
     * @param player that need to calculate score
     * @return number of row returned by numberrow method multiplied by score of this scheme card
     */
    public int calcola_punteggio(Player player) {
        return numberrow(player)*getPunteggio();
    }
}
