package main.node.Model;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Message {
    private final String MESSAGE_TYPE_SEPARATOR = "~";
    private final String ENDPOINT_SEPARATOR = "-";
    private final String ADDRESS_SEPARATOR = ":";
    private MessageType type;
    private String content;
    private int ID;

    public int getID() {
        return ID;
    }

    public Message() {}

    public Message(MessageType type, String content, int ID) {
        this.type = type;
        this.content = content;
        this.ID = ID;
    }

    public byte[] getData() {
        return (type + MESSAGE_TYPE_SEPARATOR + ID + MESSAGE_TYPE_SEPARATOR + content + '\n').getBytes();
    }


    public Message fromBytes(byte[] data) {
        System.out.println(new String(data));
        String[] split = new String(data).split(MESSAGE_TYPE_SEPARATOR);
        type = MessageType.valueOf(split[0]);
        ID = Integer.parseInt(split[1]);
        if(split.length == 3) content = split[2];
        return this;
    }

    public List<Peer> parsePeerList() {
        List<Peer> peers = new LinkedList<>();
        if(content != null) {
            String[] endpoints = content.split(ENDPOINT_SEPARATOR);
            Arrays.stream(endpoints).forEach(endpoint -> {
                String[] split = endpoint.split(ADDRESS_SEPARATOR);
                peers.add(new Peer(split[0], Integer.parseInt(split[1])));
            });
        }
        return peers;
    }

    public Peer parsePeerInfo() {
        String[] split = content.split(ADDRESS_SEPARATOR);
        //TODO Add validation
        return new Peer(split[0], Integer.parseInt(split[1]));
    }

    public MessageType getType() {
        return this.type;
    }

    String getContent() {
        return this.content;
    }

}
