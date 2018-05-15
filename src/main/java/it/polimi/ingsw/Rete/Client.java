package it.polimi.ingsw.Rete;

import it.polimi.ingsw.Game.Player;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    private String nickname;
    private String serverIP;
    private Player player;
    static int PORT=8080;

    public void connect(){
        try {
            Scanner IP=new Scanner(System.in);
            System.out.println("Input IP-Address: ");
            serverIP=IP.nextLine();
            Scanner in=new Scanner(System.in);
            System.out.println("Input your nickname:");
            nickname=in.nextLine();
            String name="Sagrada server";
            Registry registry= LocateRegistry.getRegistry("127.0.0.1",PORT);
            ServerInt stub= (ServerInt) registry.lookup(name);
            this.player=new Player(nickname);
            stub.login(player.getNickname());
            System.out.println(stub.show_waitingroom());
            int i=50;
            while (i!=0){
                Scanner numero=new Scanner(System.in);
                System.out.println("Scegli cosa fare: ");
                i=numero.nextInt();
                switch (i){
                    case 1: break;
                    case 2: break;
                    case 0: stub.logout(player.getNickname()); break;
                }
            }
        } catch (Exception e) {
            System.err.println("Client exception:   "+ e.toString());
            e.printStackTrace();
        }
    }
}
