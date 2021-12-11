package node.Model;


import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class Message {
    private MessageType type;
    private JsonElement content;
    private int ID;

    public int getID() {
        return ID;
    }

    public Message(MessageType type, JsonElement content, int ID) {
        this.type = type;
        this.content = content;
        this.ID = ID;
    }

    public byte[] getData() {
        return (new Gson().toJson(this) + '\n').getBytes();
    }

    public List<Peer> parsePeerList() {
        List<Peer> peers = new LinkedList<>();
        if(content.getAsJsonArray().size() > 0) {
            peers = Arrays.asList(new Gson().fromJson(content, Peer[].class));
        }
        return peers;
    }

    public Peer parsePeerInfo() {
        return new Gson().fromJson(content, Peer.class);
    }

    public MessageType getType() {
        return this.type;
    }

}
