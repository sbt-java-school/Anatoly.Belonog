package Sockets.Server;

import Sockets.Commands.CommandBuilder;
import Sockets.Messages.MessageBox;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Anatoly on 09.10.2016.
 */
class ServerTask implements Runnable {
    private final Socket clientSocket;
    private final MessageBox messageBox;

    ServerTask(Socket clientSocket, MessageBox messageBox) {
        this.clientSocket = clientSocket;
        this.messageBox = messageBox;
    }

    @Override
    public void run() {
        try(InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream()) {
            byte[] buffer = new byte[256];
            int charsRead;
            String command;
            String clientName = readFromClient(buffer, in);
            welcomeClient(out, clientName);
            messageBox.tryAddClient(clientName);
            CommandBuilder commandBuilder = new CommandBuilder();
            while((charsRead = in.read(buffer)) != -1) {
                command = new String(buffer, 0, charsRead);
                commandBuilder.build(command).handleServerCommand(out, in, clientName, messageBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFromClient(byte[] buffer, InputStream in) throws IOException {
        int getBytes = in.read(buffer);
        return new String(buffer, 0, getBytes);
    }

    private void welcomeClient(OutputStream out, String clientName) throws IOException {
        System.out.println("New client connected: " + clientName);
        String welcome = "Welcome, " + clientName + "! Print a command:";
        out.write(welcome.getBytes());
    }
}
