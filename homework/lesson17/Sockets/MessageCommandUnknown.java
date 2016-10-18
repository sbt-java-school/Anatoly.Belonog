package Sockets.Commands;

import Sockets.Messages.MessageBox;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Anatoly on 13.10.2016.
 */
public class MessageCommandUnknown extends CommandMessage {

    @Override
    public boolean handleServerCommand(OutputStream out, InputStream in,String clientName, MessageBox messageBox) {
        writeTo("There is no such command. Try again:", out);
        return false;
    }

    @Override
    public boolean handleClientCommand(OutputStream out, InputStream in, String clientName) {
        System.out.println(readFrom(getBuffer(), in));
        return false;
    }
}
