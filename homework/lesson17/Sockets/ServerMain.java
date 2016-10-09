package Sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
