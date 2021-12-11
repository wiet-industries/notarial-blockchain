package core.stategies;

import core.ClientHandler;
import core.Server;
import core.models.Message;

import java.util.List;

public interface ServerStrategy {
    ClientHandler processAuthor(Message message, List<ClientHandler> clientList);
    void respondToAuthor(ClientHandler client, List<ClientHandler> clientList);
    void respondToOthers(ClientHandler client, List<ClientHandler> clientList);
    void updateClients(ClientHandler client, List<ClientHandler> clientList, Server server);
}
