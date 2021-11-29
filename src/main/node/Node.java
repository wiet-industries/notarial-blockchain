package main.node;

import java.io.IOException;
import java.net.*;

public class Node implements EventListener {

    private int tcpPort;
    private int udpPort;
    private Socket tcpSocket;
    private DatagramSocket udpSocket;
    private UdpListener udpListener;
    private TcpListener tcpListener;
    private int ID;
    private InetAddress serverAddress;
    private ServerSessionHandler serverSessionHandler;
    private PeerConnectionHandler peerConnectionHandler;


    Node(int tcpPort, int udpPort, InetAddress serverAddress) {
        this.tcpPort = tcpPort;
        this.udpPort = udpPort;
        this.serverAddress = serverAddress;
        try {
            this.tcpSocket = new Socket(serverAddress, tcpPort);
            this.udpSocket = new DatagramSocket();
        } catch (IOException e) {
            System.out.println("Error while creating sockets: " + e.getMessage());
            e.printStackTrace();
        }
        this.tcpListener = new TcpListener(tcpSocket);
        this.udpListener = new UdpListener(udpSocket);
        tcpListener.subscribe(this);
        udpListener.subscribe(this);
        this.serverSessionHandler = new ServerSessionHandler(tcpSocket, udpSocket, serverAddress, udpPort);
        this.peerConnectionHandler = new PeerConnectionHandler(udpSocket);
    }

    void startNode() {
        tcpListener.start();
        udpListener.start();
    }

    void registerNode() {
        serverSessionHandler.registerNode(ID);
    }

    void requestBroadcast() {
        serverSessionHandler.requestSession(ID);
    }

    @Override
    public void update(Event event) {
        Message message = new Message().fromBytes(event.getData());
        switch (message.getType()) {
            case ID:
                //TODO add validation
                this.ID = message.getID();
                this.registerNode();
                break;
                //TODO add validation everywhere
            case NODE_LIST:
                this.peerConnectionHandler.broadcastDataToPeers(message.parsePeerList());
                break;
            case OPEN_REQUEST:
                this.peerConnectionHandler.openPort(message.parsePeerInfo());
                break;
        }
    }
}
