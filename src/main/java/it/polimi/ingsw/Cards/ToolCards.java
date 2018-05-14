package it.polimi.ingsw.Cards;

import it.polimi.ingsw.Cards.ToolCard.*;

import java.util.ArrayList;
import java.util.Random;

public class ToolCards {
    private final int dimension=12;
    private Tool[] tools= new Tool[dimension];

    public ToolCards(){
        Tool a=new ToolCard1();
        tools[0]=a;
        Tool b=new ToolCard2();
        tools[1]=b;
        Tool c=new ToolCard3();
        tools[2]=c;
        Tool d=new ToolCard4();
        tools[3]=d;
        Tool e=new ToolCard5();
        tools[4]=e;
        Tool g=new ToolCard6();
        tools[5]=g;
        Tool h=new ToolCard7();
        tools[6]=h;
        Tool i=new ToolCard8();
        tools[7]=i;
        Tool j=new ToolCard9();
        tools[8]=j;
        Tool k=new ToolCard10();
        tools[9]=k;
        Tool l=new ToolCard11();
        tools[10]=l;
        Tool m=new ToolCard12();
        tools[11]=m;
    }

    public ArrayList<Tool> extractToolCards(){
        ArrayList<Tool> tool=new ArrayList<Tool>();
        int i=0;
        while(i<3){
            Random random = new Random();
            int k = random.nextInt(tools.length);
            if(tools[k]!=null){
                tool.add(tools[k]);
                tools[k]=null;
            }
            else {
                i--;
            }
            i++;
        }
        return tool;
    }
}
