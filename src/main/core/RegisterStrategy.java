package main.core;

import java.util.List;

public class RegisterStrategy implements ServerStrategy {

    @Override
    public Client processAuthor(SocketPayload socketPayload, List<Client> clientList) {
        PayloadMessage payloadMessage = socketPayload.getPayloadMessage();
        String payloadData = payloadMessage.getMessageData();
        String[] chunks = payloadData.split(PayloadMessage.SEPARATOR);
        for (Client client : clientList) {
            if (client.getID() == Integer.parseInt(chunks[0])) {
                client.setIP(socketPayload.getAddress());
                client.setUpdPort(socketPayload.getPort());
                return client;
            }
        }
        return null;
    }

    @Override
    public boolean respondToAuthor(Client client) {
        return false;
    }

    @Override
    public boolean respondToOthers(Client client, List<Client> clientList) {
        return false;
    }

    @Override
    public boolean updateClients(Client client, List<Client> clientList) {
        return false;
    }
}
