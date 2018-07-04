package it.polimi.ingsw.gui.components.mainboard;

import it.polimi.ingsw.gui.components.variousSchemes.Windows;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Adversary extends VBox {

    private String name;
    private Windows glasswindow;

    public Adversary(String nom){
        super();
        name = nom;
        Label etich = new Label(nom);
        getChildren().addAll(etich);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Windows getGlasswindow() {
        return glasswindow;
    }

    public void setGlasswindow(Windows glasswindow) {
        this.glasswindow = glasswindow;
    }
}
