package Sockets;

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
            String command = "";
            Scanner scanner = new Scanner(System.in);
            while (!socket.isClosed()) {
                command = scanner.next();
                writeToServer(command, out);     //write a command
                if (Objects.equals(command, "read")) {
                    handleCommandRead(buffer, in, out);
                }
                else if (Objects.equals(command, "send")) {
                    handleCommandSend(buffer, in, out, scanner);
                }
                else if (Objects.equals(command, "exit")) {
                    break;
                }
                else {
                    System.out.println(readFromServer(buffer, in));
                }
                System.out.println("Print a command: ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleCommandSend(byte[] buffer, InputStream in, OutputStream out, Scanner scanner) throws IOException {
        System.out.println(readFromServer(buffer, in)); // to whom
        writeToServer(scanner.next(), out);
        System.out.println(readFromServer(buffer, in)); // message
        writeToServer(scanner.next(), out);
    }

    private void handleCommandRead(byte[] buffer, InputStream in, OutputStream out) throws IOException {
        int messagesNumber = Integer.parseInt(readFromServer(buffer, in));
        for (int i = 0; i < messagesNumber + 1; i++) {
            System.out.println(readFromServer(buffer, in));
            writeToServer(String.valueOf(i), out);
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
