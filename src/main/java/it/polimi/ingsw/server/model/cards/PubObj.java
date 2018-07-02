package it.polimi.ingsw.server.model.cards;

import it.polimi.ingsw.server.model.cards.publicCard.*;

import java.util.ArrayList;
import java.util.Random;

public class PubObj {
    private PublicObject[] publicObject= new PublicObject[10];

    public PubObj() {
        PublicObject a = new ClearShades();
        publicObject[0]=a;
        PublicObject b = new ColoredDiagonals();
        publicObject[1]=b;
        PublicObject c = new DarkShades();
        publicObject[2]=c;
        PublicObject d = new DifferentColumnsColors();
        publicObject[3]=d;
        PublicObject e = new DifferentColumnShades();
        publicObject[4]=e;
        PublicObject f = new DifferentRowColors();
        publicObject[5]=f;
        PublicObject g = new DifferentRowShades();
        publicObject[6]=g;
        PublicObject h = new DifferentShades();
        publicObject[7]=h;
        PublicObject i = new MediumShades();
        publicObject[8]=i;
        PublicObject j = new VarietyofColour();
        publicObject[9]=j;
    }

    /**
     * This method allows to extract 3 public card casually
     * @return Arraylist<PublicObject> of cards to be set in the match
     */
    public ArrayList<PublicObject> extractPubObj(){
        ArrayList<PublicObject> publicObjects = new ArrayList<PublicObject>();
        int i=0;
        final int number=3;
        while(i<number){
            Random random = new Random();
            int k = random.nextInt(publicObject.length);
            if(publicObject[k]!=null){
                publicObjects.add(publicObject[k]);
                publicObject[k]=null;
            }else {
                i--;
            }
            i++;
        }
        return publicObjects;
    }
}
