package Sockets.Commands;

/**
 * Created by Anatoly on 13.10.2016.
 */
public class CommandBuilder {
    public Command build(String commandName) {
        switch (commandName) {
            case "send": return new CommandMessageSend();
            case "read": return new CommandMessageRead();
            case "exit": return new CommandExit();
            default: return new MessageCommandUnknown();
        }
    }
}
