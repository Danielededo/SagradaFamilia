package it.polimi.ingsw.rete;

import java.rmi.RemoteException;
import java.util.TimerTask;

public class TimerTurn extends TimerTask {
    ClientInt c;
    Server s;

    public TimerTurn(ClientInt c, Server s) {
        this.c = c;
        this.s = s;
    }

    @Override
    public void run() {
        try {
            s.notify(c,"disconnettiti");
        } catch (RemoteException e) {}
    }
}
