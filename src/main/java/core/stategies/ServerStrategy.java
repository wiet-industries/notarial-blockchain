package core.stategies;

import core.ClientHandler;
import core.Server;
import core.models.SocketPayload;

import java.util.List;

public interface ServerStrategy {
    ClientHandler processAuthor(SocketPayload socketPayload, List<ClientHandler> clientList);
    void respondToAuthor(ClientHandler client, List<ClientHandler> clientList);
    void respondToOthers(ClientHandler client, List<ClientHandler> clientList);
    void updateClients(ClientHandler client, List<ClientHandler> clientList, Server server);
}
