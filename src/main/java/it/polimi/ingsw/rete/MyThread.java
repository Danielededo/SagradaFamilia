package it.polimi.ingsw.rete;

import java.rmi.RemoteException;
import java.util.ConcurrentModificationException;
import java.util.TimerTask;

public class MyThread extends TimerTask {
    private Server server;
    private boolean end=false;

    public MyThread(Server server) {
        this.server = server;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    @Override
    public void run() {
        try {
            if(end)
                this.wait();
            else
                server.vericaconnessione();
        } catch (ConcurrentModificationException e) {
        } catch (RemoteException e) {
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch(IllegalMonitorStateException e){}
    }
}
