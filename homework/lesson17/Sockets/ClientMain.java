package Sockets.Client;

import java.io.IOException;

/**
 * Created by Anatoly on 01.10.2016.
 */
public class ClientMain {

    private static final int DEFAULT_PORT = 1234;

    public static void main(String[] args) throws IOException {
        Client client = new Client("Client", DEFAULT_PORT);
        client.connect();

    }
}
