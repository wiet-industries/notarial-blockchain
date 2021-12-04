package node.Model;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class Message {
    private final String MESSAGE_TYPE_SEPARATOR = "~";
    private final String ENDPOINT_SEPARATOR = "-";
    private final String ADDRESS_SEPARATOR = ":";
    private MessageType type;
    private String content;
    private int ID;
    private final JsonObject messageData;

    public int getID() {
        return ID;
    }

    public Message(MessageType type, String content, int ID) {
        this.messageData = new JsonObject();
        this.messageData.addProperty("type", type.toString());
        this.messageData.addProperty("content", content);
        this.messageData.addProperty("id", ID);
    }

    public byte[] getData() {
        return (this.messageData.toString() + '\n').getBytes();
    }

    public Message(String data) {
        JsonParser parser = new JsonParser();
        this.messageData = parser.parse(data).getAsJsonObject();
    }

    public List<Peer> parsePeerList() {
        List<Peer> peers = new LinkedList<>();
        JsonArray clientList = this.messageData.get("content").getAsJsonArray();
        if(content != null) {
            for(int i = 0; i < clientList.size(); i++) {
                JsonObject client = clientList.get(i).getAsJsonObject();
                String ip = client.get("ip").getAsString();
                int port = client.get("port").getAsInt();
                peers.add(new Peer(ip, port));
            }
        }
        return peers;
    }

    public Peer parsePeerInfo() {
        //TODO Add validation
        return new Peer(this.getIPFromMessage(), this.getPortFromMessage());
    }

    private String getIPFromMessage() {
        return this.messageData.get("content").getAsJsonObject().get("ip").getAsString();
    }

    private int getPortFromMessage() {
        return this.messageData.get("content").getAsJsonObject().get("ip").getAsInt();
    }

    public MessageType getType() {
        return this.type;
    }

    String getContent() {
        return this.content;
    }

}
