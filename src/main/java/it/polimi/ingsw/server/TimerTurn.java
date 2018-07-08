package it.polimi.ingsw.server;

import it.polimi.ingsw.rmi.ClientInt;

import java.util.TimerTask;

public class TimerTurn extends TimerTask {
    ClientInt c;
    Hub s;

    public TimerTurn(Hub s) {
        this.s = s;
    }

    public TimerTurn(ClientInt c, Hub s) {
        this.c = c;
        this.s = s;
    }

    /**
     * This method is called during turn and disconnect client if the time is up
     */
    @Override
    public void run() {
        try {
            if(!s.isStart() && s.getRoom().getPlayers().size()>=2)
                s.getRoom().waiting_match();
            if(s.isStart()){
                s.notify(c,"Sei stato disconnesso dato che è scaduto il tempo\nPotrai riconnetterti usando le tue credenziali");
                s.notify(c,"disconnettiti");
            }
        } catch (Exception ignored) {
        }
    }
}
