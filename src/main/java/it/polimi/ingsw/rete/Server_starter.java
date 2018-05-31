package it.polimi.ingsw.rete;

public class Server_starter {
    public static void main(String[] args) {
        Server server= new Server();
        args[0]=args[0].replaceAll("-","");
        server.start_server(args[0]);
    }
}
