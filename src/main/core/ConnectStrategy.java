package main.core;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ConnectStrategy implements ServerStrategy {
    @Override
    public Client processAuthor(SocketPayload socketPayload, List<Client> clientList) {
        System.out.println("Client connected. IP: " + socketPayload.getAddress().toString() + ", PORT_TCP: " + socketPayload.getPort());
        try {
            Client client = new Client(socketPayload.getSocket(), clientList.size() + 1, clientList);
            client.start();
            return client;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean respondToAuthor(Client client, List<Client> clientList) {
        try {
            BufferedOutputStream output = client.getOutput();
            output.write(("ID~" + client.getID() + "~padding\n").getBytes(StandardCharsets.UTF_8));
            output.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean respondToOthers(Client client, List<Client> clientList) {
        return false;
    }

    @Override
    public boolean updateClients(Client client, List<Client> clientList) {
        clientList.add(client);
        return true;
    }
}
