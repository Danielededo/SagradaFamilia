package it.polimi.ingsw.gui.components.mainboard;

import it.polimi.ingsw.gui.components.variousSchemes.WindCase;
import it.polimi.ingsw.gui.components.variousSchemes.Windows;
import it.polimi.ingsw.server.utils.Constants;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Adversary extends VBox {

    private String name;
    private WindCase windowcase = new WindCase(Constants.WI_CASE, Constants.HE_CASE);
    private Windows glasswindow;

    public Adversary(String nom){
        super();
        name = nom;
        Label etich = new Label(nom);
        getChildren().addAll(etich, windowcase);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WindCase getWindowcase() {
        return windowcase;
    }

    public void setWindowcase(WindCase windowcase) {
        this.windowcase = windowcase;
    }

    public Windows getGlasswindow() {
        return glasswindow;
    }

    public void setGlasswindow(Windows glasswindow) {
        this.glasswindow = glasswindow;
    }
}
