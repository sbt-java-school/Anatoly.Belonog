package Sockets.Client;

import Sockets.Commands.CommandBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by Anatoly on 02.10.2016.
 */
class Client {
    private String name;
    private int port;

    Client(String name, int port) {

        this.name = name;
        this.port = port;
    }

    private String getName() {
        return name;
    }

    void connect() {
        byte[] buffer = new byte[100];

        try(Socket socket = new Socket("localhost", port);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream()) {

            System.out.println("Connected!");
            writeToServer(getName(), out);
            System.out.println(readFromServer(buffer, in));
            String command;
            Scanner scanner = new Scanner(System.in);
            CommandBuilder commandBuilder = new CommandBuilder();
            boolean isExit = false;
            while (!socket.isClosed() && !isExit) {
                command = scanner.next();
                writeToServer(command, out);     //write a command
                isExit = commandBuilder.build(command).handleClientCommand(out, in, getName());
                System.out.println("Print a command: ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFromServer(byte[] buffer, InputStream in) throws IOException {
        int getBytes = in.read(buffer);
        return new String(buffer, 0, getBytes);
    }

    private void writeToServer(String toServer,OutputStream out) throws IOException {
        out.write(toServer.getBytes());
    }
}
