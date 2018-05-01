package Cards;

public class GlassWindow extends Scheme {
    private String name;
    private int difficulty;
    private int link;
    private Slot[][] scheme = new Slot[4][5];

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

    public String toString() {
        return null;
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
