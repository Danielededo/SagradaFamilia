package it.polimi.ingsw.server;

import it.polimi.ingsw.server.model.game.Match;
import it.polimi.ingsw.server.model.game.Player;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Waiting_Room {
    private ArrayList<Player> players;
    private Server server;
    private Match match;


    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Waiting_Room(Server server) {
        this.server=server;
        this.match=server.match;
        players=new ArrayList<Player>();
    }

    public void addPlayer(String player) throws RemoteException, InterruptedException {
        if (status_verify()) {
            Player player1=new Player(player);
            players.add(player1);
        }
    }

    public boolean status_verify(){
        if (players.size()<4) return true;
        else return false;
    }

    @Override
    public String toString() {
        return "Waiting_Room{" +
                "players=" + players+
                '}';
    }

    public void attesa_partita() throws InterruptedException, RemoteException {
        for (int i=10;i>0;i--){
            Thread.sleep(1000);
            if (players.size()>=2){
            server.notifyObserver(""+i);
            System.out.print("\r"+i);
            }else i=0;
        }
        System.out.print("\r");
        if(players.size()==1){
            try {
                server.notifyObserver("Attendi che uno o più giocatori partecipino alla partita");
            } catch (RemoteException e) {}
        }else if(players.size()==2){
            match=new Match(players.get(0),players.get(1));
            server.getSetupGame().cancel();
            server.setStart(true);
            try {
                server.setMatch(match);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else if (players.size()==3) {
            match=new Match(players.get(0),players.get(1),players.get(2));
            server.getSetupGame().cancel();
            server.setStart(true);
            try {
                server.setMatch(match);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else if (players.size()==4){
            match=new Match(players.get(0),players.get(1),players.get(2),players.get(3));
            server.getSetupGame().cancel();
            server.setStart(true);
            try {
                server.setMatch(match);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
