package core.stategies;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import core.*;
import core.models.MessageType;
import core.models.PayloadMessage;
import core.models.SocketPayload;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class BroadcastStrategy implements ServerStrategy {
    @Override
    public ClientHandler processAuthor(SocketPayload socketPayload, List<ClientHandler> clientList) {
        PayloadMessage payloadMessage = socketPayload.getPayloadMessage();
        int ID = payloadMessage.getID();

        System.out.println("Broadcast from: " + ID);
        for (ClientHandler client : clientList) {
            if (client.getID() == ID) {
                return client;
            }
        }
        return null;
    }

    @Override
    public boolean respondToAuthor(ClientHandler client, List<ClientHandler> clientList) {
        //TODO not count clients without UDP PORT
        JsonObject message = new JsonObject();
        message.addProperty("id", client.getID());
        message.addProperty("type", MessageType.NODE_LIST.toString());
        JsonArray addresses = new JsonArray();

        for (ClientHandler c : clientList) {
            if (c != null && !c.equals(client)) {
                addresses.add(c.getClientDataAsJson());
            }
        }
        message.add("content", addresses);
        BufferedOutputStream output = client.getOutput();
        try {
            output.write(message.toString().getBytes(StandardCharsets.UTF_8));
            output.write('\n');
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean respondToOthers(ClientHandler client, List<ClientHandler> clientList) {
        for (ClientHandler c : clientList) {
            if (c != null && !c.equals(client)) {
                JsonObject response = new JsonObject();
                response.addProperty("id", c.getID());
                response.addProperty("type", MessageType.OPEN_REQUEST.toString());
                response.add("content", c.getClientDataAsJson());
                BufferedOutputStream output = c.getOutput();
                  try {
                    output.write(response.toString().getBytes(StandardCharsets.UTF_8));
                    output.write('\n');
                    output.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return true;
    }

    @Override
    public boolean updateClients(ClientHandler client, List<ClientHandler> clientList) {
        return false;
    }
}
