package it.polimi.ingsw.server.model.game;

import it.polimi.ingsw.server.model.dice.Die;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

public class Stock {
    private ArrayList<Die> dicestock;
    private Map<Integer,Die> dieMap;

    public Stock() {
        dicestock=new ArrayList<Die>();
        dieMap=new HashMap<>();
    }

    public void reset_stock(){
        dicestock.clear();
    }

    public void show_stock(){
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
        int i=1;
        for (Die die:arrayList){
            dieMap.put(i,die);
            i++;
        }
    }

    public Die extract_die(int index){
        Die temp;
        temp = dicestock.get(index);
        dicestock.remove(index);
        out.println("new Stock:");
        show_stock();
        return temp;
    }


    public Map<Integer, Die> getDieMap() {
        return dieMap;
    }

    @Override
    public String toString() {
        String string="";
        for (Die d:dicestock){
            string+=" ("+dicestock.indexOf(d)+" - "+ d+")";
        }
        return string;
    }
}
