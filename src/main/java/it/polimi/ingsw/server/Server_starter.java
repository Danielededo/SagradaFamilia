package it.polimi.ingsw.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Server_starter {
    public static void main(String[] args) {
        try {
            int timer_window=0;
            int timer_t=0;
            int timer_waiting=0;
            Properties properties=new Properties();
            InputStream is = Server_starter.class.getResourceAsStream("/timer.properties");
            try {
                properties.load(is);
                timer_window= Integer.parseInt(properties.getProperty("Timer_window"));
                timer_t= Integer.parseInt(properties.getProperty("Timer_turn"));
                timer_waiting= Integer.parseInt(properties.getProperty("Timer_waiting"));
            } catch (IOException e) {}
            if (timer_t<=0||timer_waiting<=0||timer_window<=0){
                System.err.println("Valori di uno o più timer non idonei");
                System.exit(-1);
            }
            args[0]=args[0].replaceAll("-","");
            Server server= new Server(args[0]);
            server.start_server();
        }catch (ArrayIndexOutOfBoundsException e){
            System.err.println("Non è stata inserita nessuna porta");
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
