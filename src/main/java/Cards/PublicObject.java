package Cards;

public abstract class PublicObject extends Card{
    private String effect;
    private int punteggio;

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String toString() {
        return "PublicObject{" +
                "name=" + super.getName()+
                ", effect='" + effect + '\'' +
                '}';
    }
    public abstract int calcola_punteggio(GlassWindow scheme);
}
