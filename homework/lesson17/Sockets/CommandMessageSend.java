package Sockets.Commands;

import Sockets.Messages.MessageBox;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * Created by Anatoly on 13.10.2016.
 */
public class CommandMessageSend extends CommandMessage {

    @Override
    public boolean handleServerCommand(OutputStream out, InputStream in, String clientName, MessageBox messageBox) {
        writeTo("Print to whom: ", out);
        String recipientName = readFrom(getBuffer(), in);
        writeTo("Print a message: ", out);
        String message = readFrom(getBuffer(), in);
        messageBox.put(recipientName, clientName, message);
        return false;
    }

    @Override
    public boolean handleClientCommand(OutputStream out, InputStream in, String clientName) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(readFrom(getBuffer(), in)); // to whom
        writeTo(scanner.next(), out);
        System.out.println(readFrom(getBuffer(), in)); // message
        writeTo(scanner.next(), out);
        //scanner.close();
        return false;
    }
}
