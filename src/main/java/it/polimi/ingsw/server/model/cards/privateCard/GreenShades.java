package it.polimi.ingsw.server.model.cards.privateCard;

import it.polimi.ingsw.utils.Colour;
import it.polimi.ingsw.server.model.cards.PrivateObject;

public class GreenShades extends PrivateObject{
    public GreenShades() {
        super.setValue(3);
        super.setName("Sfumature Verdi");
        super.setColor(Colour.GREEN);
        super.setEffect("Somma dei valori su tutti i dadi verdi");
    }
}
