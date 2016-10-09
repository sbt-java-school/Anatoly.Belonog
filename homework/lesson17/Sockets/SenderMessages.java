package Sockets;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Anatoly on 03.10.2016.
 */
class SenderMessages {
    private final String name;
    private final ArrayList<String> messages;

    SenderMessages(String name, String message) {
        this.name = name;
        ArrayList<String> temporary = new ArrayList<>();
        temporary.add(message);
        this.messages = new ArrayList<>(temporary);
    }

    void put(String message) {
        messages.add(message);
    }

    String getName() {
        return name;
    }

    ArrayList<String> getMessagesWithName() {
        return messages.stream().map(message -> name + " > " + message).collect(Collectors.toCollection(ArrayList::new));
    }
}
