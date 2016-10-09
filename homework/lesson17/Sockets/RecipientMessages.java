package Sockets;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Anatoly on 03.10.2016.
 */
class RecipientMessages {
    private final String name;
    private final ArrayList<SenderMessages> sendersMessages;

    RecipientMessages(String name) {
        this.name = name;
        this.sendersMessages = new ArrayList<>();
    }

    String getName() {
        return name;
    }

    void put(String sender, String message) {
        for (SenderMessages senderMessages :
                sendersMessages) {
            if (Objects.equals(senderMessages.getName(), sender)) {
                senderMessages.put(message);
                return;
            }
        }
        sendersMessages.add(new SenderMessages(sender, message));
    }

    ArrayList<String> getAllMessagesWithSender() {
        ArrayList<String> allMessages = new ArrayList<>();
        for (SenderMessages messages :
                sendersMessages) {
            allMessages.addAll(messages.getMessagesWithName());
        }
        return allMessages;
    }
}
