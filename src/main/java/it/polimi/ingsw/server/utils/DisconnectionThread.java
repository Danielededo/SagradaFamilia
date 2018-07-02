package it.polimi.ingsw.server.utils;

import it.polimi.ingsw.server.Hub;

import java.rmi.RemoteException;
import java.util.ConcurrentModificationException;
import java.util.TimerTask;

public class DisconnectionThread extends TimerTask {
    private Hub server;

    public DisconnectionThread(Hub server) {
        this.server = server;
    }

    @Override
    public void run() {
        try {
            server.vericaconnessione();
        } catch (ConcurrentModificationException | RemoteException | IllegalMonitorStateException e) {
        }
    }
}
