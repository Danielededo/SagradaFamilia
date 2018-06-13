package it.polimi.ingsw.server;

import it.polimi.ingsw.server.Server;

public class Server_starter {
    public static void main(String[] args) {
        Server server= new Server();
        args[0]=args[0].replaceAll("-","");
        server.start_server(args[0]);
    }
}
