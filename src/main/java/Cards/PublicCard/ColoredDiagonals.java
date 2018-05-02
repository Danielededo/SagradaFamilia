package Cards.PublicCard;

import Cards.GlassWindow;
import Cards.PublicObject;

public class ColoredDiagonals extends PublicObject {
    public ColoredDiagonals() {
        setName("Colored Diagonals");
        setValue(9);
        setEffect("Number of same-colored dice diagonally adjacent");
    }

    public int calcola_punteggio(GlassWindow scheme) {
        int cont=0;
        for (int i=1;i<3;i++){
            for (int j=1;j<4;j++){
                if (scheme.getSlot(i,j).isOccupate()){
                    if (scheme.getSlot(i,j).getDice().getDicecolor()==scheme.getSlot(i-1,j-1).getDice().getDicecolor()) {
                        cont++;
                    } else if (scheme.getSlot(i,j).getDice().getDicecolor()==scheme.getSlot(i-1,j+1).getDice().getDicecolor()) {
                        cont++;
                    } else if (scheme.getSlot(i,j).getDice().getDicecolor()==scheme.getSlot(i+1,j-1).getDice().getDicecolor()) {
                        cont++;
                    } else if (scheme.getSlot(i,j).getDice().getDicecolor()==scheme.getSlot(i+1,j+1).getDice().getDicecolor()) {
                        cont++;
                    }
                }
            }
        }
        for (int i=1;i<4;i++){
            if (scheme.getSlot(0,i).isOccupate()){
                if (scheme.getSlot(0,i).getDice().getDicecolor()==scheme.getSlot(1,i-1).getDice().getDicecolor()){
                    cont++;
                }else if (scheme.getSlot(0,i).getDice().getDicecolor()==scheme.getSlot(1,i+1).getDice().getDicecolor()){
                    cont++;
                }
            }
        }
        for (int i=1;i<3;i++){
            if (scheme.getSlot(i,4).isOccupate()){
                if (scheme.getSlot(i,4).getDice().getDicecolor()==scheme.getSlot(i-1,3).getDice().getDicecolor()){
                    cont++;
                }else if (scheme.getSlot(i,4).getDice().getDicecolor()==scheme.getSlot(i+1,3).getDice().getDicecolor()){
                    cont++;
                }
            }
        }
        for (int i=1;i<4;i++){
            if (scheme.getSlot(3,i).isOccupate()){
                if (scheme.getSlot(3,i).getDice().getDicecolor()==scheme.getSlot(2,i-1).getDice().getDicecolor()){
                    cont++;
                }else if (scheme.getSlot(3,i).getDice().getDicecolor()==scheme.getSlot(2,i+1).getDice().getDicecolor()){
                    cont++;
                }
            }
        }
        for (int i=1;i<3;i++){
            if (scheme.getSlot(i,0).isOccupate()){
                if (scheme.getSlot(i,0).getDice().getDicecolor()==scheme.getSlot(i-1,1).getDice().getDicecolor()){
                    cont++;
                }else if (scheme.getSlot(i,0).getDice().getDicecolor()==scheme.getSlot(i+1,1).getDice().getDicecolor()){
                    cont++;
                }
            }
        }
        if (scheme.getSlot(0,0).isOccupate() && scheme.getSlot(0,0).getDice().getDicecolor()==scheme.getSlot(1,1).getDice().getDicecolor()){
            cont++;
        }
        if (scheme.getSlot(0,4).isOccupate() && scheme.getSlot(0,4).getDice().getDicecolor()==scheme.getSlot(1,3).getDice().getDicecolor()){
            cont++;
        }
        if (scheme.getSlot(3,0).isOccupate() && scheme.getSlot(3,0).getDice().getDicecolor()==scheme.getSlot(2,1).getDice().getDicecolor()){
            cont++;
        }
        if (scheme.getSlot(3,4).isOccupate() && scheme.getSlot(3,4).getDice().getDicecolor()==scheme.getSlot(2,3).getDice().getDicecolor()){
            cont++;
        }
        return cont;
    }
}
