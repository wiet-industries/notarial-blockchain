package main.core;

public class PayloadMessage {
    static public String SEPARATOR = "~";
    private final MessageType messageType;
    private final String messageData;


    public PayloadMessage(String message) {
        String[] chunks = message.trim().split(SEPARATOR);
        this.messageType = MessageType.valueOf(chunks[0]);
        this.messageData = message.replace(chunks[0] + "~", "");
    }

    public MessageType getMessageType() {
        return this.messageType;
    }

    public String getMessageData() {
        return this.messageData;
    }
}
