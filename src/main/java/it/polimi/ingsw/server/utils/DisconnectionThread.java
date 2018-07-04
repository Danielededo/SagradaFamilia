package it.polimi.ingsw.server.utils;

import it.polimi.ingsw.server.Hub;

import java.rmi.RemoteException;
import java.util.ConcurrentModificationException;
import java.util.TimerTask;

public class DisconnectionThread extends TimerTask {
    private Hub hub;

    public DisconnectionThread(Hub hub) {
        this.hub = hub;
    }

    @Override
    public void run() {
        try {
            hub.connection_verify();
        } catch (ConcurrentModificationException | RemoteException | IllegalMonitorStateException e) {
        }
    }
}
