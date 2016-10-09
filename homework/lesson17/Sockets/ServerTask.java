package Sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
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
            String clientName = readFromClient(buffer, in);
            welcomeClient(out, clientName);
            messageBox.tryAddClient(clientName);
            int charsRead;
            String command;
            while((charsRead = in.read(buffer)) != -1) {
                command = new String(buffer, 0, charsRead);
                handleCommand(out, in, command, clientName);
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
        writeToClient("Welcome, " + clientName + "! Print a command:", out);
    }

    private void writeToClient(String toClient ,OutputStream out) throws IOException {
        out.write(toClient.getBytes());
    }

    private boolean handleCommand(OutputStream out, InputStream in, String command, String clientName) throws IOException {
        System.out.println("Handling command...");
        byte[] buffer = new byte[20];
        if (Objects.equals(command, "send")) {
            handleCommandSend(out, in, clientName, buffer);
        }
        else if (Objects.equals(command, "read")) {
            handleCommandRead(out, clientName, in);
        }
        else if (Objects.equals(command, "exit")) {
            Thread.currentThread().interrupt();
            return true;
        }
        else {
            writeToClient("There is no such command. Try again:", out);
        }
        return false;
    }

    private void handleCommandRead(OutputStream out, String clientName, InputStream in) throws IOException {
        byte[] buffer = new byte[10];
        ArrayList<String> allMessangesToRecipient = messageBox.getAllMessangesToRecipient(clientName);
        Integer size = allMessangesToRecipient.size();
        writeToClient(size.toString(), out);
        if (size == 0) {
            writeToClient("There's no messages for you", out);
            readFromClient(buffer, in);
        }
        else {
            writeToClient("There are messages for you:", out);
            System.out.println(readFromClient(buffer, in));
            for (String message :
                    allMessangesToRecipient) {
                writeToClient(message, out);
                readFromClient(buffer, in);
            }
        }
    }

    private void handleCommandSend(OutputStream out, InputStream in, String clientName, byte[] buffer) throws IOException {
        writeToClient("Print to whom: ", out);
        String recipientName = readFromClient(buffer, in);
        writeToClient("Print a message: ", out);
        String message = readFromClient(buffer, in);
        messageBox.put(recipientName, clientName, message);
    }
}
