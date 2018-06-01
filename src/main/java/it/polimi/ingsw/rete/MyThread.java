package it.polimi.ingsw.rete;

import java.rmi.RemoteException;
import java.util.ConcurrentModificationException;
import java.util.TimerTask;

public class MyThread extends TimerTask {
    private Server server;

    public MyThread(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        if(server.getListofobserver().size()==0){
            System.out.println("lista degli osservatori vuota");
        }
        try {
            server.vericaconnessione();
        } catch (ConcurrentModificationException e) {
        } catch (RemoteException e) {
        }
    }
}
