package main.node;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Message {
    private final String TYPE_SEPARATOR = "~";
    private final String ENDPOINT_SEPARATOR = "-";
    private MessageType type;
    private String content;

    Message() {}

    Message(MessageType type, String content) {
        this.type = type;
        this.content = content;
    }

    byte[] getData() {
        return (type + TYPE_SEPARATOR + content + '\n').getBytes();
    }

    Message fromBytes(byte[] data) {
        String[] split = new String(data).split(TYPE_SEPARATOR);
        type = MessageType.valueOf(split[0]);
        content = split[1];
        return new Message(type, content);
    }

    List<Peer> parsePeerList() {
        String[] endpoints = content.split(ENDPOINT_SEPARATOR);
        List<Peer> peers = new LinkedList<>();
        Arrays.stream(endpoints).forEach(endpoint -> {
            String[] split = endpoint.split(":");
            peers.add(new Peer(split[0], Integer.parseInt(split[1])));
        });
        return peers;
    }

    Peer parsePeerInfo() {
        String[] split = content.split(":");
        return new Peer(split[0], Integer.parseInt(split[1]));
    }

    MessageType getType() {
        return this.type;
    }

    String getContent() {
        return this.content;
    }

}
