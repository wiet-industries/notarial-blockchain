package core.stategies;

import com.google.gson.Gson;
import core.ClientHandler;
import core.Server;
import core.models.MessageType;
import core.models.MessageContent;
import core.models.Message;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ConnectStrategy implements ServerStrategy {
    @Override
    public ClientHandler processAuthor(Message message, List<ClientHandler> clientList) {
        System.out.println("Client connected. IP: " + message.getAddress().toString() + ", PORT_TCP: " + message.getPort());
        try {
            ClientHandler client = new ClientHandler(message.getSocket(), clientList.size() + 1, clientList);
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
            MessageContent response = new MessageContent(MessageType.ID, new Gson().toJsonTree(Integer.toString(client.getID())), client.getID());
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
