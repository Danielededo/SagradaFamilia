package it.polimi.ingsw.rete;

import it.polimi.ingsw.game.Match;
import it.polimi.ingsw.game.Player;

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

    public int numberPlayers() {
        return players.size();
    }

    public void addPlayer(String player) throws RemoteException, InterruptedException {
        int cont;
        if (status_verify()) {
            Player player1=new Player(player);
            players.add(player1);
            for (int i=0;i<players.size();i++){
                cont=0;
                for (String s:server.getName_disconnected()){
                    if (s.equals(players.get(i).getNickname())){
                        cont=1;
                    }
                }
                if (cont==0) players.remove(i);
            }
            if (this.players.size()==2)
            attesa_partita();
        }else System.out.println("Max giocatori");
    }

    public void deleteplayer(String player) {
        for (Player p:players){
            if(p.getNickname().equals(player)){
                players.remove(p);
                return;
            }
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
    public void attesa_player(){
        boolean gone=true;
        while (gone){
        }
    }

    public void attesa_partita() throws InterruptedException, RemoteException {
        do {
            for (int i=10;i>=0;i--){
                Thread.sleep(1000);
                server.notifyObserver(""+i);
                System.out.println(i);
            }
            try {
                server.control();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if(players.size()==1){
                try {
                    server.notifyObserver("Wait before a player join");
                    attesa_player();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        } while (players.size()<2);
        if (players.size()==2){
            match=new Match(players.get(0),players.get(1));
            try {
                server.setMatch(match);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else if (players.size()==3) {
            match=new Match(players.get(0),players.get(1),players.get(2));
            try {
                server.setMatch(match);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else if (players.size()==4){
            match=new Match(players.get(0),players.get(1),players.get(2),players.get(3));
            try {
                server.setMatch(match);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
