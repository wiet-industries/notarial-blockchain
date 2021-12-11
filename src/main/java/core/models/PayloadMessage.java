package core.models;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class PayloadMessage {
    private static final Integer NOT_INITIALIZED_ID = null;
    private final MessageType type;
    private final JsonElement content;
    private Integer ID = NOT_INITIALIZED_ID;

    public PayloadMessage(MessageType type, JsonElement content, int ID) {
        this.type = type;
        this.content = content;
        this.ID = ID;
    }

    public PayloadMessage(MessageType type, JsonElement content) {
        this.type = type;
        this.content = content;
    }

    public MessageType getMessageType() {
        return this.type;
    }

    public int getID() {
        return this.ID;
    }

    public JsonElement getContent() {
        return this.content;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
