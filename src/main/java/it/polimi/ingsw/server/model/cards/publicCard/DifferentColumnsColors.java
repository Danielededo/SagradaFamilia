package it.polimi.ingsw.server.model.cards.publicCard;

import it.polimi.ingsw.server.model.cards.PublicObject;
import it.polimi.ingsw.utils.Colour;
import it.polimi.ingsw.server.model.game.Player;

import java.util.ArrayList;

public class DifferentColumnsColors extends PublicObject {
    public DifferentColumnsColors() {
        setName("Colori diversi - Colonna");
        setValue(2);
        setPunteggio(5);
        setEffect("Colonne senza colori ripetuti");
    }

    /**
     * Calculate number of column with every dice colour
     * @param player from which to take the scheme card
     * @return int cont number of column
     */
    private int numbercolumns(Player player){
        int cont=0;
        ArrayList<Colour> colori = new ArrayList<Colour>();
        for (int i=0;i<height;i++){
            colori.add(Colour.RED);
            colori.add(Colour.BLUE);
            colori.add(Colour.YELLOW);
            colori.add(Colour.GREEN);
            colori.add(Colour.PURPLE);
            for (int j=0;j<width;j++){
                if (player.getWindow().getSlot(j,i).isOccupate() &&
                        colori.contains(player.getWindow().getSlot(j,i).getDice().getDicecolor())){
                    colori.remove(player.getWindow().getSlot(j,i).getDice().getDicecolor());
                }
            }
            if (colori.size()==1) {
                cont++;
            }
            colori.clear();
        }
        return cont;
    }

    /**
     * Calculate score of player scheme card in @param
     * @param player that need to calculate score
     * @return int column number multiplied by score of this scheme card
     */
    public int calcola_punteggio(Player player) {
        return numbercolumns(player)*getPunteggio();
    }
}
