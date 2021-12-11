package core.stategies;

import com.google.gson.JsonArray;
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
    public void respondToAuthor(ClientHandler client, List<ClientHandler> clientList) {
        //TODO not count clients without UDP PORT
        JsonArray addresses = new JsonArray();

        for (ClientHandler c : clientList) {
            if (c != null && !c.equals(client)) {
                addresses.add(c.getClientConnectionDataAsJson());
            }
        }
        PayloadMessage message = new PayloadMessage(MessageType.NODE_LIST, addresses, client.getID());
        BufferedOutputStream output = client.getOutput();
        try {
            output.write(message.toJson().getBytes(StandardCharsets.UTF_8));
            output.write('\n');
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void respondToOthers(ClientHandler client, List<ClientHandler> clientList) {
        for (ClientHandler c : clientList) {
            if (c != null && !c.equals(client)) {
                PayloadMessage response = new PayloadMessage(MessageType.OPEN_REQUEST, client.getClientConnectionDataAsJson(), c.getID());
                BufferedOutputStream output = c.getOutput();
                  try {
                    output.write(response.toJson().getBytes(StandardCharsets.UTF_8));
                    output.write('\n');
                    output.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void updateClients(ClientHandler client, List<ClientHandler> clientList, Server server) {

    }
}
