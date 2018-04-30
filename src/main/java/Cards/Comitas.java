package Cards;

import Dice.Colour;

public class Comitas extends GlassWindow {
    public void createComitas(){
        super.setName("Comitas");
        super.setLink(11);
        super.setDifficulty(5);
        createSlot00();
    }
    public void createSlot00(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.YELLOW);
        super.setSlot(c,0,0);
        createSlot01();
    }
    public void createSlot01(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,0,1);
        createSlot02();
    }
    public void createSlot02(){
        Slot c = new Slot();
        c.setValue(2);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,0,2);
        createSlot03();
    }
    public void createSlot03(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,0,3);
        createSlot04();
    }
    public void createSlot04(){
        Slot c = new Slot();
        c.setValue(6);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,0,4);
        createSlot10();
    }
    public void createSlot10(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,1,0);
        createSlot11();
    }
    public void createSlot11(){
        Slot c = new Slot();
        c.setValue(4);
        c.setSlotcolour(Colour.YELLOW);
        super.setSlot(c,1,1);
        createSlot12();
    }
    public void createSlot12(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,1,2);
        createSlot13();
    }
    public void createSlot13(){
        Slot c = new Slot();
        c.setValue(5);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,1,3);
        createSlot14();
    }
    public void createSlot14(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.YELLOW);
        super.setSlot(c,1,4);
        createSlot20();
    }
    public void createSlot20(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,2,0);
        createSlot21();
    }
    public void createSlot21(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,2,1);
        createSlot22();
    }
    public void createSlot22(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,2,2);
        createSlot23();
    }
    public void createSlot23(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.YELLOW);
        super.setSlot(c,2,3);
        createSlot24();
    }
    public void createSlot24(){
        Slot c = new Slot();
        c.setValue(5);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,2,4);
        createSlot30();
    }
    public void createSlot30(){
        Slot c = new Slot();
        c.setValue(1);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,3,0);
        createSlot31();
    }
    public void createSlot31(){
        Slot c = new Slot();
        c.setValue(2);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,3,1);
        createSlot32();
    }
    public void createSlot32(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.YELLOW);
        super.setSlot(c,3,2);
        createSlot33();
    }
    public void createSlot33(){
        Slot c = new Slot();
        c.setValue(3);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,3,3);
        createSlot34();
    }
    public void createSlot34(){
        Slot c = new Slot();
        c.setValue(0);
        c.setSlotcolour(Colour.WHITE);
        super.setSlot(c,3,4);
    }
}
