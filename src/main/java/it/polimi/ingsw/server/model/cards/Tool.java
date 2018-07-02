package it.polimi.ingsw.server.model.cards;

import it.polimi.ingsw.server.utils.Colour;
import it.polimi.ingsw.server.model.dice.Die;
import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Player;
import it.polimi.ingsw.server.model.game.Stock;

public abstract class Tool extends Card{
    private boolean accessed=false;
    private boolean used=false;  //for the payment of the favor token, if use==true the payment has already happened
    private String name;
    private String effect;
    private Player player;
    protected String error="";
    protected static final String[] list__of_errors={
            "Non puoi utilizzare questa carta tool perchè non possiedi abbastanza segnalini favore",
            "Non puoi cambiare un 6 in 1 o un 1 in 6, scegli un nuovo dado",
            "Lo slot selezionato per posizionare il dado possiede già un dado",
            "Lo slot selezionato per prendere il dado non possiede un dado",
            "Non puoi posizionare il dado in questa casella",
            "Il dado non può essere posizionato in questo slot perchè non soddisfa le regole di posizionamento",
            "Il dado selezionato non può essere spostato in questa casella",
            "Non puoi utilizzare l'effetto della carta perchè non è il tuo secondo turno",
            "Non puoi utilizzare questa carta nel tuo secondo turno",
            "Intero errato o slot già occupato",
            "Non puoi usare questa tool card ora, non ci sono dadi nel round track",
            "Non puoi scegliere questo dado",
            "Scegli un altro slot",
            "Non puoi scegliere dadi con colore differente, riprova",
            "Non ci sono dadi sufficienti per usare questa tool card"
    };


    public String getError() {
        return error;
    }

    public void setAccessed(boolean accessed) {
        this.accessed = accessed;
    }

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isUsed() {

        return used;
    }

    public abstract boolean effect(Die dado1, Die dado2, boolean plusminus, Match partita, Stock stock, Slot slot1, Slot slot2, Slot slot3, Slot slot4, int value);



    @Override
    public String toString() {
        String escape = Colour.RED.escape();
        if (accessed){
            return  "Nome: " +escape+ name + Colour.RESET +
                    " effetto:\n" + effect + "\nPer usare questa carta sono necessari"+Colour.PURPLE.escape() +" due segnalini favore"+Colour.RESET+'\n';
        }else return "Nome: " +escape+ name + Colour.RESET +
                " effetto:\n" + effect + "\nPer usare questa carta è necessario"+Colour.PURPLE.escape() +" un segnalino favore"+Colour.RESET+'\n';
    }
}
