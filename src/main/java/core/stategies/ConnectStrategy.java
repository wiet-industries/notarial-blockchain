package core.stategies;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import core.ClientHandler;
import core.Server;
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
    public void respondToAuthor(ClientHandler client, List<ClientHandler> clientList) {
        try {
            PayloadMessage response = new PayloadMessage(MessageType.ID, new Gson().toJsonTree(Integer.toString(client.getID())), client.getID());
            BufferedOutputStream output = client.getOutput();
            output.write(response.toJson().getBytes(StandardCharsets.UTF_8));
            output.write('\n');
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void respondToOthers(ClientHandler client, List<ClientHandler> clientList) {
    }

    @Override
    public void updateClients(ClientHandler clientHandler, List<ClientHandler> clientList, Server server) {
        clientList.add(clientHandler);
    }
}
