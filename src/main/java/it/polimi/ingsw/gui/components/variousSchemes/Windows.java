package it.polimi.ingsw.gui.components.variousSchemes;

import it.polimi.ingsw.gui.components.panels.DieG;
import it.polimi.ingsw.gui.components.panels.Tassel;
import it.polimi.ingsw.utils.Constants;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Windows extends GridPane{
    private int difficulty;
    private String name;
    private ArrayList<Tassel> list = new ArrayList<>();

    private int selected = -1;

    private DieG update;


    public Windows(){
        for(int i = 0; i < Constants.F_SLOT; i++){
            list.add(new Tassel(i));
        }

    //prima riga
        this.add(list.get(0),0,0);

        this.add(list.get(1),1,0);

        this.add(list.get(2),2,0);

        this.add(list.get(3),3,0);

        this.add(list.get(4),4,0);


    //seconda riga

        this.add(list.get(5),0,1);

        this.add(list.get(6),1,1);

        this.add(list.get(7),2,1);

        this.add(list.get(8),3,1);

        this.add(list.get(9),4,1);


    //terza riga

        this.add(list.get(10),0,2);

        this.add(list.get(11),1,2);

        this.add(list.get(12),2,2);

        this.add(list.get(13),3,2);

        this.add(list.get(14),4,2);


    //quarta riga

        this.add(list.get(15),0,3);

        this.add(list.get(16),1,3);

        this.add(list.get(17),2,3);

        this.add(list.get(18),3,3);

        this.add(list.get(19),4,3);

        this.setGridLinesVisible(true);
        this.setPadding(new Insets(10,10,10,10));
    }


    public DieG getUpdate() {
        return update;
    }

    public void setUpdate(DieG update) {
        this.update = update;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public ArrayList<Tassel> getList() {
        return list;
    }

    public void soltOffIt(Windows w){
        selected = -1;
    }

    public int getSelected() {
        return selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}