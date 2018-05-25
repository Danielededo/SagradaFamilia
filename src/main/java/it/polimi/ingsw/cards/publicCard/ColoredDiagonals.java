package it.polimi.ingsw.cards.publicCard;

import it.polimi.ingsw.cards.PublicObject;
import it.polimi.ingsw.game.Player;

public class ColoredDiagonals extends PublicObject {
    public ColoredDiagonals() {
        setName("Colored Diagonals");
        setValue(9);
        setEffect("Number of same-colored dice diagonally adjacent");
    }

    public int calcola_punteggio(Player player) {
        int cont=0;
        for (int i=1;i<width-1;i++){
            for (int j=1;j<width;j++){
                if (player.getWindow().getSlot(i,j).isOccupate()){
                    if (player.getWindow().getSlot(i-1,j-1).isOccupate()&&player.getWindow().getSlot(i,j).getDice().getDicecolor()==player.getWindow().getSlot(i-1,j-1).getDice().getDicecolor()) {
                        cont++;
                    } else if (player.getWindow().getSlot(i-1,j+1).isOccupate()&&player.getWindow().getSlot(i,j).getDice().getDicecolor()==player.getWindow().getSlot(i-1,j+1).getDice().getDicecolor()) {
                        cont++;
                    } else if (player.getWindow().getSlot(i+1,j-1).isOccupate()&&player.getWindow().getSlot(i,j).getDice().getDicecolor()==player.getWindow().getSlot(i+1,j-1).getDice().getDicecolor()) {
                        cont++;
                    } else if (player.getWindow().getSlot(i+1,j+1).isOccupate()&&player.getWindow().getSlot(i,j).getDice().getDicecolor()==player.getWindow().getSlot(i+1,j+1).getDice().getDicecolor()) {
                        cont++;
                    }
                }
            }
        }
        for (int i=1;i<width;i++){
            if (player.getWindow().getSlot(0,i).isOccupate()){
                if (player.getWindow().getSlot(1,i-1).isOccupate()&&player.getWindow().getSlot(0,i).getDice().getDicecolor()==player.getWindow().getSlot(1,i-1).getDice().getDicecolor()){
                    cont++;
                }else if (player.getWindow().getSlot(1,i+1).isOccupate()&&player.getWindow().getSlot(0,i).getDice().getDicecolor()==player.getWindow().getSlot(1,i+1).getDice().getDicecolor()){
                    cont++;
                }
            }
        }
        for (int i=1;i<width-1;i++){
            if (player.getWindow().getSlot(i,4).isOccupate()){
                if (player.getWindow().getSlot(i-1,3).isOccupate()&&player.getWindow().getSlot(i,4).getDice().getDicecolor()==player.getWindow().getSlot(i-1,3).getDice().getDicecolor()){
                    cont++;
                }else if (player.getWindow().getSlot(i+1,3).isOccupate()&&player.getWindow().getSlot(i,4).getDice().getDicecolor()==player.getWindow().getSlot(i+1,3).getDice().getDicecolor()){
                    cont++;
                }
            }
        }
        for (int i=1;i<width;i++){
            if (player.getWindow().getSlot(3,i).isOccupate()){
                if (player.getWindow().getSlot(2,i-1).isOccupate()&&player.getWindow().getSlot(3,i).getDice().getDicecolor()==player.getWindow().getSlot(2,i-1).getDice().getDicecolor()){
                    cont++;
                }else if (player.getWindow().getSlot(2,i+1).isOccupate()&&player.getWindow().getSlot(3,i).getDice().getDicecolor()==player.getWindow().getSlot(2,i+1).getDice().getDicecolor()){
                    cont++;
                }
            }
        }
        for (int i=1;i<width-1;i++){
            if (player.getWindow().getSlot(i,0).isOccupate()){
                if (player.getWindow().getSlot(i-1,1).isOccupate()&&player.getWindow().getSlot(i,0).getDice().getDicecolor()==player.getWindow().getSlot(i-1,1).getDice().getDicecolor()){
                    cont++;
                }else if (player.getWindow().getSlot(i+1,1).isOccupate()&&player.getWindow().getSlot(i,0).getDice().getDicecolor()==player.getWindow().getSlot(i+1,1).getDice().getDicecolor()){
                    cont++;
                }
            }
        }
        if (player.getWindow().getSlot(0,0).isOccupate() && player.getWindow().getSlot(1,1).isOccupate() && player.getWindow().getSlot(0,0).getDice().getDicecolor()==player.getWindow().getSlot(1,1).getDice().getDicecolor()){
            cont++;
        }
        if (player.getWindow().getSlot(0,4).isOccupate() && player.getWindow().getSlot(1,3).isOccupate() && player.getWindow().getSlot(0,4).getDice().getDicecolor()==player.getWindow().getSlot(1,3).getDice().getDicecolor()){
            cont++;
        }
        if (player.getWindow().getSlot(3,0).isOccupate() && player.getWindow().getSlot(2,1).isOccupate() && player.getWindow().getSlot(3,0).getDice().getDicecolor()==player.getWindow().getSlot(2,1).getDice().getDicecolor()){
            cont++;
        }
        if (player.getWindow().getSlot(3,4).isOccupate() && player.getWindow().getSlot(2,3).isOccupate() && player.getWindow().getSlot(3,4).getDice().getDicecolor()==player.getWindow().getSlot(2,3).getDice().getDicecolor()){
            cont++;
        }
        return cont;
    }
}
