package core.stategies;

import core.ClientHandler;
import core.Server;
import core.models.Message;

import java.util.List;

public class ServerStrategyContext {
    ServerStrategy strategy;

    public ServerStrategyContext () {}

    public void setStrategy(ServerStrategy strategy) {
        this.strategy = strategy;
    }

    public void execute(Message message, List<ClientHandler> clientList, Server server) {
        ClientHandler client = this.strategy.processAuthor(message, clientList);
        //TODO make chain of calls
        if(client == null) {
            System.err.println("Author not found");
            return;
        }
        this.strategy.respondToAuthor(client, clientList);
        this.strategy.respondToOthers(client, clientList);
        this.strategy.updateClients(client, clientList, server);
    }
}
