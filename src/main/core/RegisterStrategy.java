package main.core;

import java.util.List;

public class RegisterStrategy implements ServerStrategy {

    @Override
    public Client processAuthor(SocketPayload socketPayload, List<Client> clientList) {
        PayloadMessage payloadMessage = socketPayload.getPayloadMessage();
        String payloadData = payloadMessage.getMessageData();
        int ID = Integer.parseInt(payloadData);
        for (Client client : clientList) {
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
    public boolean respondToAuthor(Client client, List<Client> clientList) {
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
