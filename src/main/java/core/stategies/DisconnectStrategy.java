package core.stategies;

import core.ClientHandler;
import core.Server;
import core.models.Message;
import core.models.MessageContent;

import java.util.List;

public class DisconnectStrategy implements ServerStrategy{
    @Override
    public ClientHandler processAuthor(Message message, List<ClientHandler> clientList) {
        MessageContent messageContent = message.getMessageContent();
        int ID = messageContent.getID();

        System.out.println("Disconnect from: " + ID);
        for (ClientHandler client : clientList) {
            if (client.getID() == ID) {
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
        clientList.remove(client);
    }
}
