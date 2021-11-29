package main.core;

public class PayloadMessage {
    static public String SEPARATOR = "~";
    private final MessageType messageType;
    private final String messageData;


    public PayloadMessage(String message) {
        //TODO handle accept
//        if(message.length() == 0) {
//            this.messageType = MessageType.CONNECT;
//            this.messageData = "";
//            return;
//        }
        String[] chunks = message.trim().split(SEPARATOR);
        this.messageType = MessageType.valueOf(chunks[0]);
        this.messageData = chunks[1];//message.replace(chunks[0] + "~", "");
    }

    public MessageType getMessageType() {
        return this.messageType;
    }

    public String getMessageData() {
        return this.messageData;
    }
}
