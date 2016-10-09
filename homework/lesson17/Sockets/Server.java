package Sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Anatoly on 02.10.2016.
 */
class Server {
    private final int port;
    private final MessageBox messageBox;

    Server(int port) {
        this.port = port;
        this.messageBox = new MessageBox();
    }

    void start() {
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            waitForClient(serverSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void waitForClient(ServerSocket serverSocket) throws IOException {
        Socket clientSocket;
        while(!serverSocket.isClosed()) {
            if ((clientSocket = serverSocket.accept()) != null) {
                new Thread(new ServerTask(clientSocket, messageBox)).start();
            }
        }
    }
}
