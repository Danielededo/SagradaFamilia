package it.polimi.ingsw.server.model.game;

import it.polimi.ingsw.server.model.dice.Die;

import java.util.ArrayList;

import static java.lang.System.out;

public class Stock {
    private ArrayList<Die> dicestock;

    public Stock() {
        dicestock=new ArrayList<Die>();
    }

    public void reset_stock(){
        dicestock.clear();
    }

    public void show_riserva(){
        int i=0;
        for (Die d : dicestock){
            out.println("index"+i+": "+d.toString());
            i++;
        }
    }

    public ArrayList<Die> getDicestock() {
        return dicestock;
    }

    public void setDicestock(ArrayList<Die> arrayList){
        this.dicestock= arrayList;
    }

    public Die extract_die(int index){
        Die temp;
        temp = dicestock.get(index);
        dicestock.remove(index);
        out.println("new Stock:");
        show_riserva();
        return temp;
    }

    @Override
    public String toString() {
        String stringa="";
        for (Die d:dicestock){
            stringa+=" ("+dicestock.indexOf(d)+" - "+ d+")";
        }
        return stringa;
    }
}
