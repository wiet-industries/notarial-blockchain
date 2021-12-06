package core.models;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import core.models.MessageType;
import org.json.JSONException;

import java.io.IOException;

public class PayloadMessage {
    private final MessageType type;
    private final JsonElement content;
    private int ID;


    public PayloadMessage(MessageType type, JsonElement content, int ID) {
        this.type = type;
        this.content = content;
        this.ID = ID;
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

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
