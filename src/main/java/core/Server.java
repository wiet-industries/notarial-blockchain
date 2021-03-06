package core;

import core.connection.NewClientEventManager;
import core.connection.UdpEventManager;
import core.stategies.*;
import core.utils.EventListener;
import core.models.Event;
import core.models.MessageType;
import core.models.MessageContent;
import core.models.Message;

import java.io.IOException;
import java.net.InetAddress;
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
    private List<InetAddress> authorizedClients;

    public Server(int tcpPort, int udpPort, List<InetAddress> authorizedClients) throws IOException {
        this.udpEventManager = new UdpEventManager(udpPort);
        this.tcpListener = new NewClientEventManager(tcpPort);
        this.tcpPort = tcpPort;
        this.udpPort = udpPort;
        this.udpEventManager.subscribe(this);
        this.tcpListener.subscribe(this);
        this.serverStrategyContext = new ServerStrategyContext();
        this.authorizedClients = authorizedClients;
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
        Message message = event.getPayload();
        MessageContent messageContent = message.getMessageContent();
        this.setProperStrategy(messageContent.getMessageType());
        this.serverStrategyContext.execute(message, this.clientList, this);
    }

    private void setProperStrategy(MessageType messageType) {
        switch (messageType) {
            case CONNECT:
                this.serverStrategyContext.setStrategy(new ConnectStrategy());
                break;
            case DISCONNECT:
                this.serverStrategyContext.setStrategy(new DisconnectStrategy());
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

    public boolean checkIfAuthorized(InetAddress ip) {
        for(InetAddress existingIP : this.authorizedClients) {
            if(ip.toString().equals(existingIP.toString())) {
                return true;
            }
        }
        return false;
    }
}
