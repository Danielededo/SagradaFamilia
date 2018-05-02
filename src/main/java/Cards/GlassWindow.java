package Cards;

import java.util.Arrays;

public class GlassWindow extends Scheme {
    private String name;
    private  static final int width = 4;
    private static final int height = 5;
    private int difficulty;
    private int link;
    private Slot[][] scheme = new Slot[width][height];

    public void setLink(int link) {
        this.link = link;
    }

    public int getLink() {
        return link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setSlot(Slot scheme, int i, int j) {
        this.scheme[i][j] = scheme;
        scheme.setLine(i);
        scheme.setColumn(j);
    }

    @Override
    public String toString() {
        return "GlassWindow{" +
                "name='" + name + '\'' +
                ", difficulty=" + difficulty +
                ", scheme=" + Arrays.toString(scheme) +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public Slot getSlot(int i, int j) {
        return scheme[i][j];
    }

}
