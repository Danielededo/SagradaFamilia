package it.polimi.ingsw.Cards;

import it.polimi.ingsw.Cards.PrivateCard.*;

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
        PrivateObject privateObjects= new PrivateObject();
        Random random=new Random();
        int k= random.nextInt(privateObject.length);
        int i=0;
        while(i==0){
        if(privateObject[k]!=null){
            privateObjects=privateObject[k];
            i++;
        }
        else{
            i--;
        }
        i++;
        }
        return privateObjects;
    }
}
