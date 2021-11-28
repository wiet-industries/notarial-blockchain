package main.core;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Server implements EventListener{
    private final int tcpPort;
    private final int udpPort;
    private TcpEventManager tcpListener;
    private UdpEventManager udpEventManager;
    private ServerStrategyContext serverStrategyContext;
    private List<Client> clientList = new LinkedList<>();

    public Server(int tcpPort, int udpPort) throws IOException {
        this.udpEventManager = new UdpEventManager(udpPort);
        this.tcpListener = new TcpEventManager(tcpPort);
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
        SocketPayload data = event.getPayload();
        Socket clientSocket = data.getSocket();
        PayloadMessage payloadMessage = data.getPayloadMessage();
        this.handleClientSocket(clientSocket);
        this.setProperStrategy(payloadMessage.getMessageType());
        this.serverStrategyContext.execute(data, this.clientList);
    }

    public void handleClientSocket(Socket clientSocket) {
        if (clientSocket == null) {
            return;
        }
        if (!this.checkIfClientExists(clientSocket)){
            try{
                System.out.println("Adding new client with the ID: " + clientList.size() + 1);
                Client client = new Client(clientSocket, clientList.size() + 1, this.clientList);
                this.clientList.add(client);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Cannot create new client with the given socket");
            }
        }

    }

    private boolean checkIfClientExists(Socket socket) {
        for(Client client : this.clientList) {
            if (socket.equals(client.getSocket())) {
                return true;
            }
        }
        return false;
    }

    private void setProperStrategy(MessageType messageType) {
        switch (messageType) {
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
