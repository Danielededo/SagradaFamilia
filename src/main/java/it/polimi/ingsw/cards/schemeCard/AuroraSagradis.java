package it.polimi.ingsw.Cards.SchemeCard;

import it.polimi.ingsw.Dice.Colour;
import it.polimi.ingsw.Cards.GlassWindow;
import it.polimi.ingsw.Cards.Slot;

public class AuroraSagradis extends GlassWindow {
    public AuroraSagradis(){
        super.setName("Aurora Sagradis");
        super.setLink(6);
        super.setDifficulty(4);
        createSlot00();
    }
    private void createSlot00(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.RED);
        super.setSlot(c,0,0);
        createSlot01();
    }
    private void createSlot01(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,0,1);
        createSlot02();
    }
    private void createSlot02(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.BLUE);
        super.setSlot(c,0,2);
        createSlot03();
    }
    private void createSlot03(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,0,3);
        createSlot04();
    }
    private void createSlot04(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.YELLOW);
        super.setSlot(c,0,4);
        createSlot10();
    }
    private void createSlot10(){
        Slot c = new Slot();
        c.setValue(4);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,1,0);
        createSlot11();
    }
    private void createSlot11(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.PURPLE);
        super.setSlot(c,1,1);
        createSlot12();
    }
    private void createSlot12(){
        Slot c = new Slot();
        c.setValue(3);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,1,2);
        createSlot13();
    }
    private void createSlot13(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.GREEN);
        super.setSlot(c,1,3);
        createSlot14();
    }
    private void createSlot14(){
        Slot c = new Slot();
        c.setValue(2);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,1,4);
        createSlot20();
    }
    private void createSlot20(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,2,0);
        createSlot21();
    }
    private void createSlot21(){
        Slot c = new Slot();
        c.setValue(1);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,2,1);
        createSlot22();
    }
    private void createSlot22(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,2,2);
        createSlot23();
    }
    private void createSlot23(){
        Slot c = new Slot();
        c.setValue(5);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,2,3);
        createSlot24();
    }
    private void createSlot24(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,2,4);
        createSlot30();
    }
    private void createSlot30(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,3,0);
        createSlot31();
    }
    private void createSlot31(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,3,1);
        createSlot32();
    }
    private void createSlot32(){
        Slot c = new Slot();
        c.setValue(6);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,3,2);
        createSlot33();
    }
    private void createSlot33(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,3,3);
        createSlot34();
    }
    private void createSlot34(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,3,4);
    }
}
