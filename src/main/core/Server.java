package main.core;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server implements EventListener{
    final ServerSocket serverSocket;
    final DatagramSocket datagramSocket;
    List<Client> clients = Collections.synchronizedList(new ArrayList<>());
    TcpEventManager tcpListener;
    UdpEventManager udpEventManager;
    ServerStrategyContext serverStrategyContext;



    public Server(int tcpPort, int udpPort) throws IOException {
        this.udpEventManager = new UdpEventManager(udpPort);
        this.udpEventManager.subscribe(this);
        this.serverStrategyContext = new ServerStrategyContext();
        serverSocket = new ServerSocket(tcpPort);
        datagramSocket = new DatagramSocket(udpPort);
    }

    void startServer() {
        tcpListener = new TcpEventManager(serverSocket, clients);
        tcpListener.start();
        System.out.println("Server started with TCP_PORT: " + serverSocket.getLocalPort());
        updListener.start();
        System.out.println("Server started with UPD_PORT: " + datagramSocket.getLocalPort());
    }

    void stopServer() {
        //TODO
    }

    @Override
    public void update(Event event) {
        SocketPayload data = event.getPayload();

        this.serverStrategyContext.execute(data);
    }

    public void setProperStrategy(SocketPayload data) {
        // parsowanie
        data.getMessage()
    }
}
