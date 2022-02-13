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
    public ClientHandler processAuthor(Message message, List<ClientHandler> clientList, Server server) {
        System.out.println("Client connected. IP: " + message.getAddress().toString() + ", PORT_TCP: " + message.getPort());
        try {
            if (server.checkIfAuthorized(message.getAddress())) {
                message.getSocket().close();
                throw new IllegalArgumentException("This address is not authorized");
            }
            ClientHandler client = new ClientHandler(message.getSocket(), this.findNextID(clientList), clientList);
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

    private int findNextID(List<ClientHandler> clientList) {
        int result = 0;
        for(ClientHandler client : clientList) {
            if(client.getID() > result) {
                result = client.getID();
            }
        }
        return result + 1;
    }
}
