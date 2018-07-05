package it.polimi.ingsw.server;

import java.rmi.RemoteException;
import java.util.ConcurrentModificationException;
import java.util.TimerTask;

public class DisconnectionThread extends TimerTask {
    private Hub hub;

    public DisconnectionThread(Hub hub) {
        this.hub = hub;
    }

    /**
     * This thread check if client is alive by call method connection_verify in hub
     */
    @Override
    public void run() {
        try {
            hub.connection_verify();
        } catch (ConcurrentModificationException | RemoteException | IllegalMonitorStateException e) {
        }
    }
}
