package Sockets.Commands;

import Sockets.Messages.MessageBox;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Anatoly on 13.10.2016.
 */
public class CommandExit implements Command {
    @Override
    public boolean handleServerCommand(OutputStream out, InputStream in, String command, MessageBox messageBox) {
        return true;
    }

    @Override
    public boolean handleClientCommand(OutputStream out, InputStream in, String clientName) {
        return true;
    }
}
