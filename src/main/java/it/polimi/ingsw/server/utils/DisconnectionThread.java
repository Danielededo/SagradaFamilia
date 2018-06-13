package it.polimi.ingsw.server.utils;

import it.polimi.ingsw.server.Server;

import java.rmi.RemoteException;
import java.util.ConcurrentModificationException;
import java.util.TimerTask;

public class DisconnectionThread extends TimerTask {
    private Server server;

    public DisconnectionThread(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        try {
            server.vericaconnessione();
        } catch (ConcurrentModificationException e) {
        } catch (RemoteException e) {
        } catch(IllegalMonitorStateException e){}
    }
}
