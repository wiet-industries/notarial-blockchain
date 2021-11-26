package main.core;

public class ServerStrategyContext {
    ServerStrategy strategy;

    public ServerStrategyContext () {}

    public void setStrategy(ServerStrategy strategy) {
        this.strategy = strategy;
    }

    public void execute(SocketPayload socketPayload) {
        this.strategy.processAuthor(socketPayload.getMessage());
        this.strategy.respondToAuthor();
        this.strategy.respondToOthers();
        this.strategy.updateClients();
    }
}
