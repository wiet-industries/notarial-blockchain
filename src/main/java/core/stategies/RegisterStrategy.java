package core.stategies;

import core.ClientHandler;
import core.models.PayloadMessage;
import core.models.SocketPayload;

import java.util.List;

public class RegisterStrategy implements ServerStrategy {

    @Override
    public ClientHandler processAuthor(SocketPayload socketPayload, List<ClientHandler> clientList) {
        PayloadMessage payloadMessage = socketPayload.getPayloadMessage();
        // TODO change to GSON
        int ID = payloadMessage.getID();
        for (ClientHandler client : clientList) {
            if (client.getID() == ID) {
                client.setIP(socketPayload.getAddress());
                client.setUpdPort(socketPayload.getPort());
                System.out.println("Register client ID: " + ID + ", IP:" + socketPayload.getAddress().toString() + ", PORT: " + socketPayload.getPort());
                return client;
            }
        }
        return null;
    }

    @Override
    public boolean respondToAuthor(ClientHandler client, List<ClientHandler> clientList) {
        return false;
    }

    @Override
    public boolean respondToOthers(ClientHandler client, List<ClientHandler> clientList) {
        return false;
    }

    @Override
    public boolean updateClients(ClientHandler client, List<ClientHandler> clientList) {
        return false;
    }
}
