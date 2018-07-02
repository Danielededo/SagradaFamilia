package it.polimi.ingsw.gui.components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class ComponentG extends ImageView{
    private String name;
    private String path;


    public ComponentG(String nome, String perc){
        name = nome;
        path = perc;
        setImage(new Image(path));
        setVisible(true);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}