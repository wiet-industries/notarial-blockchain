package core.stategies;

import core.ClientHandler;
import core.models.SocketPayload;

import java.util.List;

public interface ServerStrategy {
    ClientHandler processAuthor(SocketPayload socketPayload, List<ClientHandler> clientList);
    boolean respondToAuthor(ClientHandler client, List<ClientHandler> clientList);
    boolean respondToOthers(ClientHandler client, List<ClientHandler> clientList);
    boolean updateClients(ClientHandler client, List<ClientHandler> clientList);
}
