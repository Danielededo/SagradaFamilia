package it.polimi.ingsw.rete;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    private String nickname;
    private String serverIP;
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
            Registry registry= LocateRegistry.getRegistry(serverIP,PORT);
            ServerInt stub= (ServerInt) registry.lookup(name);
            boolean connesso=stub.login(nickname);
            if (!connesso){
                System.err.println("Nickname already exists... bye");
                return;
            }
            System.out.println(stub.show_waitingroom());
            int i=50;
            while (i!=0){
                Scanner numero=new Scanner(System.in);
                System.out.print("Choose among the following : \n'0' for logging out\n");
                i=numero.nextInt();
                switch (i){
                    case 1: break;
                    case 2: break;
                    case 0: stub.logout(nickname); break;
                    }
            }
        } catch (Exception e) {
            System.err.println("Client exception:   "+ e.toString());
            e.printStackTrace();
        }
    }
}
