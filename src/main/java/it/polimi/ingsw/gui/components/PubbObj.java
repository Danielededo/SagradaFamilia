package it.polimi.ingsw.gui.components;

public class PubbObj extends ComponentG {
    private int value;

    public PubbObj(String nome, String perc){
        super(nome,perc);
        setFitWidth(220);
        setFitHeight(220);
        setPreserveRatio(true);
    }

}