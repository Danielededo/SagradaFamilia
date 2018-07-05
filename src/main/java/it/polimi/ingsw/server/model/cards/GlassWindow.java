package it.polimi.ingsw.server.model.cards;

import it.polimi.ingsw.utils.Colour;

public class GlassWindow  {
    private String name;
    private  static final int width = 4;
    private static final int height = 5;
    private int difficulty;
    private int link;
    private Slot[][] scheme = new Slot[width][height];

    /**
     * This method calculate number of empty slots in this window
     * @return int number of empty slot
     */
    public int calculateEmptySlot(){
        int a=0;
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                if(!scheme[i][j].isOccupate())
                    a++;
            }
        }
        return -a;
    }

    public void setLink(int link) {
        this.link = link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setSlot(Slot slot,int i, int j){
        this.scheme[i][j]=slot;
        scheme[i][j].setLine(i);
        scheme[i][j].setColumn(j);
    }

    /**
     * This method creates single Slot of Glasswindow
     * @param value Int value of Slot that has row i and column j
     * @param colour Colour of Slot that has row i and column j
     * @param i int row
     * @param j int column
     */
    public void setSlot(int value,Colour colour, int i, int j) {
        Slot a=new Slot();
        this.scheme[i][j]=a;
        this.scheme[i][j].setSlotcolour(colour);
        this.scheme[i][j].setValue(value);
        scheme[i][j].setLine(i);
        scheme[i][j].setColumn(j);
    }

    @Override
    public String toString() {
        String escape = Colour.RED.escape();
        String string = "\n";
        for (int i=0;i<width;i++){
            for (int j=0;j<height;j++){
                string += scheme[i][j].toString()+ "   ";
            }
            string+="\n";
        }
        return  "Nome: '"+escape + name + Colour.RESET+'\'' +
                ", difficoltà=" + difficulty + string;
    }

    public String getName() {
        return name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public Slot getSlot(int i, int j){
        Slot provv = new Slot();
        if(i == -1 || i == 4 || j == -1 || j == 5)
            {provv.setExists(false);
             provv.setOccupate(false);
             provv.setSlotcolour(Colour.WHITE);
             provv.setValue(0);
             return provv;}
        else
             return scheme[i][j];
        }

    public Slot getSlot(Slot slot){
        return slot;
    }
}
