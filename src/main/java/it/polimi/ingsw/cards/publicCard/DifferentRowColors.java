package it.polimi.ingsw.cards.publicCard;

import it.polimi.ingsw.cards.PublicObject;
import it.polimi.ingsw.dice.Colour;
import it.polimi.ingsw.game.Player;

import java.util.ArrayList;

public class DifferentRowColors extends PublicObject {
    public DifferentRowColors() {
        setName("Different Colors - Row");
        setValue(1);
        setPunteggio(6);
        setEffect("Rows without repeated colors");
    }

    private int numerorighe(Player player) {
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

    public int calcola_punteggio(Player player) {
        return numerorighe(player)*getPunteggio();
    }
}
