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
        //TODO make chain of calls
        if(client == null) {
            System.err.println("Author not found");
            return;
        }
        boolean respondToAuthorStatus = this.strategy.respondToAuthor(client, clientList);
        boolean respondToOthersStatus = this.strategy.respondToOthers(client, clientList);
        boolean clientUpdateStatus = this.strategy.updateClients(client, clientList);
    }
}
