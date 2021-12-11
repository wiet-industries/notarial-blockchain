package core.stategies;

import core.ClientHandler;
import core.Server;
import core.models.MessageContent;
import core.models.Message;

import java.util.List;

public class RegisterStrategy implements ServerStrategy {

    @Override
    public ClientHandler processAuthor(Message message, List<ClientHandler> clientList) {
        MessageContent payloadMessage = message.getMessageContent();
        // TODO change to GSON
        int ID = payloadMessage.getID();
        for (ClientHandler client : clientList) {
            if (client.getID() == ID) {
                client.setIP(message.getAddress());
                client.setUpdPort(message.getPort());
                System.out.println("Register client ID: " + ID + ", IP:" + message.getAddress().toString() + ", PORT: " + message.getPort());
                return client;
            }
        }
        return null;
    }

    @Override
    public void respondToAuthor(ClientHandler client, List<ClientHandler> clientList) {

    }

    @Override
    public void respondToOthers(ClientHandler client, List<ClientHandler> clientList) {

    }

    @Override
    public void updateClients(ClientHandler client, List<ClientHandler> clientList, Server server) {
        synchronized (clientList) {
            int index = clientList.indexOf(client);
            clientList.get(index).subscribe(server);
        }
    }
}
