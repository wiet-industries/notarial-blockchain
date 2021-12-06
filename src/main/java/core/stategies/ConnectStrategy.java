package core.stategies;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import core.ClientHandler;
import core.models.MessageType;
import core.models.PayloadMessage;
import core.models.SocketPayload;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ConnectStrategy implements ServerStrategy {
    @Override
    public ClientHandler processAuthor(SocketPayload socketPayload, List<ClientHandler> clientList) {
        System.out.println("Client connected. IP: " + socketPayload.getAddress().toString() + ", PORT_TCP: " + socketPayload.getPort());
        try {
            ClientHandler client = new ClientHandler(socketPayload.getSocket(), clientList.size() + 1, clientList);
            client.start();
            return client;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean respondToAuthor(ClientHandler client, List<ClientHandler> clientList) {
        try {
            PayloadMessage response = new PayloadMessage(MessageType.ID, new Gson().toJsonTree(Integer.toString(client.getID())), client.getID());
            BufferedOutputStream output = client.getOutput();
            output.write(response.toString().getBytes(StandardCharsets.UTF_8));
            output.write('\n');
            output.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean respondToOthers(ClientHandler client, List<ClientHandler> clientList) {
        return false;
    }

    @Override
    public boolean updateClients(ClientHandler clientHandler, List<ClientHandler> clientList) {
        clientList.add(clientHandler);
        return true;
    }
}
