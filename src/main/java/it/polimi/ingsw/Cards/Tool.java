package it.polimi.ingsw.Cards;

public class Tool {
    private boolean accessed=false;
    private String name;
    private String effect;

    public void setAccessed(boolean accessed) {
        this.accessed = accessed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public boolean isAccessed() {
        return accessed;
    }

    public String getEffect() {
        return effect;
    }
}
