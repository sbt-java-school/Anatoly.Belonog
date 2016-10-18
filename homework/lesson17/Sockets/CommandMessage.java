package Sockets.Commands;

import Sockets.Messages.MessageBox;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Anatoly on 13.10.2016.
 */
public abstract class CommandMessage implements Command {
    private byte[] buffer = new byte[256];

    protected String readFrom(byte[] buffer, InputStream in) {
        try {
            int getBytes = in.read(buffer);
            return new String(buffer, 0, getBytes);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void writeTo(String toServer,OutputStream out) {
        try {
            out.write(toServer.getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected byte[] getBuffer() {
        return new byte[256];
    }
}
