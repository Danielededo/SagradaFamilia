package it.polimi.ingsw.server;

public class Server_starter {
    public static void main(String[] args) {
        try {
            args[0]=args[0].replaceAll("-","");
            Server server= new Server(args[0]);
            server.start_server();
        }catch (ArrayIndexOutOfBoundsException e){
            System.err.println("Non è stata inserita nessuna porta");
            System.exit(-1);
        }
    }
}
