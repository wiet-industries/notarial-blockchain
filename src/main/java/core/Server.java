package core;

import core.connection.NewClientEventManager;
import core.connection.UdpEventManager;
import core.utils.EventListener;
import core.models.Event;
import core.models.MessageType;
import core.models.MessageContent;
import core.models.Message;
import core.stategies.BroadcastStrategy;
import core.stategies.ConnectStrategy;
import core.stategies.RegisterStrategy;
import core.stategies.ServerStrategyContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server implements EventListener {
    private final int tcpPort;
    private final int udpPort;
    private NewClientEventManager tcpListener;
    private UdpEventManager udpEventManager;
    private ServerStrategyContext serverStrategyContext;
    private List<ClientHandler> clientList = Collections.synchronizedList(new ArrayList<>());

    public Server(int tcpPort, int udpPort) throws IOException {
        this.udpEventManager = new UdpEventManager(udpPort);
        this.tcpListener = new NewClientEventManager(tcpPort);
        this.tcpPort = tcpPort;
        this.udpPort = udpPort;
        this.udpEventManager.subscribe(this);
        this.tcpListener.subscribe(this);
        this.serverStrategyContext = new ServerStrategyContext();
    }

    void startServer() {
        tcpListener.start();
        System.out.println("Server started with TCP_PORT: " + this.tcpPort);
        udpEventManager.start();
        System.out.println("Server started with UPD_PORT: " + this.udpPort);
    }

    void stopServer() {
        //TODO
    }

    @Override
    public void update(Event event) {
        Message data = event.getPayload();
        MessageContent payloadMessage = data.getMessageContent();
        this.setProperStrategy(payloadMessage.getMessageType());
        this.serverStrategyContext.execute(data, this.clientList, this);
    }


    private void setProperStrategy(MessageType messageType) {
        switch (messageType) {
            case CONNECT:
                this.serverStrategyContext.setStrategy(new ConnectStrategy());
                break;
            case REGISTER:
                this.serverStrategyContext.setStrategy(new RegisterStrategy());
                break;
            case BROADCAST:
                this.serverStrategyContext.setStrategy(new BroadcastStrategy());
                break;
            default:
                System.out.println("Message Type not supported");
        }
    }
}
