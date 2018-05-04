package Game;

import Dice.Die;

import java.util.ArrayList;

import static java.lang.System.out;

public class Stock {
    private ArrayList<Die> dicestock;

    public Stock(int n) {
        dicestock=new ArrayList<Die>(2*n+1);
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

    public void addDie(Die d){
        dicestock.add(d);
    }

    public Die extract_die(int index){
        Die temp=new Die();
        temp = dicestock.get(index);
        dicestock.remove(index);
        out.println("new Stock:");
        show_riserva();
        return temp;
    }
}
