package Sockets.Server;

import Sockets.Messages.MessageBox;
import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Anatoly on 02.10.2016.
 */
class Server {
    public static final int NUMBER_OF_THREADS = 1;
    private final int port;
    private final MessageBox messageBox;
    private final ExecutorService service = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

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
                service.submit(new ServerTask(clientSocket, messageBox));
                //new Thread(new ServerTask(clientSocket, messageBox)).start();
            }
        }
    }
}
