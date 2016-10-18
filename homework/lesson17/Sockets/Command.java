package Sockets.Commands;

import Sockets.Messages.MessageBox;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Anatoly on 13.10.2016.
 */
public interface Command {
    public boolean handleServerCommand(OutputStream out, InputStream in, String clientName, MessageBox messageBox);
    public boolean handleClientCommand(OutputStream out, InputStream in, String clientName);
}
