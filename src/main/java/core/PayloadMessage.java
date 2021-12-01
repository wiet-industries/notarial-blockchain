package core;

import org.json.JSONObject;

public class PayloadMessage {
    static public String SEPARATOR = "~";
    private final MessageType messageType;
    private final String messageData;


    public PayloadMessage(String message) {
        JSONObject jo = new JSONObject(message);
        this.messageType = MessageType.valueOf((String) jo.opt("type"));
        this.messageData = message;
    }

    public MessageType getMessageType() {
        return this.messageType;
    }

    public String getMessageData() {
        return this.messageData;
    }
}
