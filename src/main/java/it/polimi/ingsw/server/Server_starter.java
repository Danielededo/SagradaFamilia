package it.polimi.ingsw.server;

public class Server_starter {
    public static void main(String[] args) {
        try {
            args[0]=args[0].replaceAll("-","");
            String timer_waiting=args[1];
            String timer_window=args[2];
            String timer_t=args[3];
            Server server= new Server(args[0],timer_window,timer_waiting,timer_t);
            server.start_server();
        }catch (ArrayIndexOutOfBoundsException e){
            System.err.println("Valori di configurazione iniziali mancanti");
            System.exit(-1);
        }catch (NumberFormatException e){
            System.err.println("Valori di uno o più timer non idonei");
            System.exit(-1);
        }catch (NullPointerException e){
            System.err.println("file missing");
            System.exit(-1);
        }
    }
}
