package main.core;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ConnectStrategy implements ServerStrategy {
    @Override
    public Client processAuthor(SocketPayload socketPayload, List<Client> clientList) {
        System.out.println("Client connected. IP: " + socketPayload.getAddress().toString() + ", PORT_TCP: " + socketPayload.getPort());
        Client client = null;
        try {
            client = new Client(socketPayload.getSocket(), clientList.size() + 1, clientList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.start();
        return client;
    }

    @Override
    public boolean respondToAuthor(Client client, List<Client> clientList) {
        BufferedOutputStream output = client.getOutput();
        try {
            System.out.println(("ID~" + client.getID() + "\n"));
            output.write(("ID~" + client.getID() + "~gfkgfkhj\n").getBytes(StandardCharsets.UTF_8));
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
        return false;
    }
}
