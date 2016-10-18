package Sockets.Commands;

import Sockets.Messages.MessageBox;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Anatoly on 13.10.2016.
 */
public class CommandMessageRead extends CommandMessage {

    @Override
    public boolean handleServerCommand(OutputStream out, InputStream in, String clientName, MessageBox messageBox) {
        ArrayList<String> allMessangesToRecipient = messageBox.getAllMessangesToRecipient(clientName);
        Integer size = allMessangesToRecipient.size();
        writeTo(size.toString(), out);
        if (size == 0) {
            writeTo("There's no messages for you", out);
            readFrom(getBuffer(), in);
        }
        else {
            writeTo("There are messages for you:", out);
            System.out.println(readFrom(getBuffer(), in));
            for (String message :
                    allMessangesToRecipient) {
                writeTo(message, out);
                readFrom(getBuffer(), in);
            }
        }
        return false;
    }

    @Override
    public boolean handleClientCommand(OutputStream out, InputStream in, String clientName) {
        int messagesNumber = Integer.parseInt(readFrom(getBuffer(), in));
        for (int i = 0; i < messagesNumber + 1; i++) {
            System.out.println(readFrom(getBuffer(), in));
            writeTo(String.valueOf(i), out);
        }
        return false;
    }
}
