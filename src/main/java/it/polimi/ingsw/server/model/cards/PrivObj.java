package it.polimi.ingsw.server.model.cards;

import it.polimi.ingsw.server.model.cards.privateCard.*;

import java.util.Random;

public class PrivObj {
    private PrivateObject[] privateObject = new PrivateObject[5];

    public PrivObj() {
        PrivateObject a = new BlueShades();
        privateObject[0]=a;
        PrivateObject b = new GreenShades();
        privateObject[1]=b;
        PrivateObject c = new PurpleShades();
        privateObject[2]=c;
        PrivateObject d = new RedShades();
        privateObject[3]=d;
        PrivateObject e = new YellowShades();
        privateObject[4]=e;
    }

    public PrivateObject extractPrivObj(){
        PrivateObject privateObjects=null;
        Random random=new Random();
        int i=0;
        while(i==0){
            int k= random.nextInt(privateObject.length);
            if(privateObject[k]!=null){
                privateObjects=privateObject[k];
                privateObject[k]=null;
            } else{
                i--;
            }
            i++;
        }
        return privateObjects;
    }
}
