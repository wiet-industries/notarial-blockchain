package main.core;

import java.util.List;

public class ServerStrategyContext {
    ServerStrategy strategy;

    public ServerStrategyContext () {}

    public void setStrategy(ServerStrategy strategy) {
        this.strategy = strategy;
    }

    public void execute(SocketPayload socketPayload, List<Client> clientList) {
        Client client = this.strategy.processAuthor(socketPayload, clientList);
        boolean respondToAuthorStatus = this.strategy.respondToAuthor(client);
        boolean respondToOthersStatus = this.strategy.respondToOthers(client, clientList);
        boolean clientUpdateStatus = this.strategy.updateClients(client, clientList);
    }
}
