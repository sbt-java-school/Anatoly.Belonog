package Sockets;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Anatoly on 02.10.2016.
 */
class MessageBox {
    private final ArrayList<RecipientMessages> recipientsMessages;

    MessageBox() {
        recipientsMessages = new ArrayList<>();
    }

    private RecipientMessages getRecipientMessages(String recipientName) {
        for (RecipientMessages messages :
                recipientsMessages) {
            if (Objects.equals(messages.getName(), recipientName)) {
                return messages;
            }
        }

        RecipientMessages recipientMessages = new RecipientMessages(recipientName);
        recipientsMessages.add(recipientMessages);
        return recipientMessages;
    }

    void put(String recipient, String sender, String message) {
        for (RecipientMessages messages:
             recipientsMessages) {
            if (Objects.equals(messages.getName(), recipient)) {
                messages.put(sender, message);
                return;
            }
        }
    }

    ArrayList<String> getAllMessangesToRecipient(String name) {
        return getRecipientMessages(name).getAllMessagesWithSender();
    }

    void tryAddClient(String clientName) {
        for (RecipientMessages messages:
                recipientsMessages) {
            if (Objects.equals(messages.getName(), clientName)) {
                return;
            }
        }
        recipientsMessages.add(new RecipientMessages(clientName));
    }
}
