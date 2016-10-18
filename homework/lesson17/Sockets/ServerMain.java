package Sockets.Server;

import java.io.IOException;

/**
 * Created by Anatoly on 01.10.2016.
 */
public class ServerMain {

    public static final int DEFAULT_PORT = 1234;

    public static void main(String[] args) throws IOException {
        Server server = new Server(DEFAULT_PORT);
        server.start();
    }
}
