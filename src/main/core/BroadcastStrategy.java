package main.core;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class BroadcastStrategy implements ServerStrategy {
    @Override
    public Client processAuthor(SocketPayload socketPayload, List<Client> clientList) {
        String[] payloadMessage = socketPayload.getPayloadMessage().getMessageData().split(PayloadMessage.SEPARATOR); // XDDD
        int ID = Integer.parseInt(payloadMessage[0]);
        System.out.println("Broadcast from: " + ID);
        for (Client client : clientList) {
            if (client.getID() == ID) {
                return client;
            }
        }
        return null;
    }

    @Override
    public boolean respondToAuthor(Client client, List<Client> clientList) {
        String message = "NODE_LIST~" + client.getID() + "~"; //TODO not count clients without UDP PORT
        for (Client c : clientList) {
            if (c != null && client.getID() != c.getID()) {
                if (message.charAt(message.length() - 1) != '~') {
                    message += "-";
                }
                message += c.getIP().toString().substring(1) + ":" + c.getUpdPort();
            }
        }
        BufferedOutputStream output = client.getOutput();
        try {
            output.write(message.getBytes(StandardCharsets.UTF_8));
            output.write('\n');
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean respondToOthers(Client client, List<Client> clientList) {
        for (Client c : clientList) {
            if (c != null && client.getID() != c.getID()) {
                BufferedOutputStream output = c.getOutput();
                String message = "OPEN_REQUEST~" + c.getID() + "~" + client.getIP().toString().substring(1) + ":" + client.getUpdPort();
                try {
                    output.write(message.getBytes(StandardCharsets.UTF_8));
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
    public boolean updateClients(Client client, List<Client> clientList) {
        return false;
    }
}
