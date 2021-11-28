package main.core;

import java.util.List;

public interface ServerStrategy {
    Client processAuthor(SocketPayload socketPayload, List<Client> clientList);
    boolean respondToAuthor(Client client);
    boolean respondToOthers(Client client, List<Client> clientList);
    boolean updateClients(Client client, List<Client> clientList);
}
