package core.models;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import core.models.MessageType;
import org.json.JSONException;

import java.io.IOException;

public class PayloadMessage {
    private final JsonObject messageData;
    private static JsonParser parser = new JsonParser();


    public PayloadMessage(String message) throws IOException, JSONException {

        this.messageData = parser.parse(message).getAsJsonObject();
        if(!this.checkIfValidMessage()) {
            throw new IOException("Payload data has no required fields");
        }
    }

    public MessageType getMessageType() {
        return MessageType.valueOf(this.messageData.get("type").getAsString());
    }

    public int getID() {
        return this.messageData.get("id").getAsInt();
    }

    public String getContent() {
        return this.messageData.get("content").getAsString();
    }

    private boolean checkIfValidMessage() {
        return this.messageData.has("id") && this.messageData.has("type") && this.messageData.has("content");
    }
}
