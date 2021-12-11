package node;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import node.Listeners.TcpListener;
import node.Listeners.UdpListener;
import node.Model.Event;
import node.Model.Message;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class Node implements EventListener {
    private final int tcpPort;
    private final int udpPort;
    private final InetAddress serverAddress;
    private UdpListener udpListener;
    private TcpListener tcpListener;
    private ServerSessionHandler serverSessionHandler;
    private PeerConnectionHandler peerConnectionHandler;
    private Socket tcpSocket;
    private DatagramSocket udpSocket;
    private int ID;

    public Node(int tcpPort, int udpPort, InetAddress serverAddress) {
        this.tcpPort = tcpPort;
        this.udpPort = udpPort;
        this.serverAddress = serverAddress;
    }

    public int getTcpPort() {
        return tcpPort;
    }

    public int getUdpPort() {
        return udpPort;
    }

    public int getID() {
        return ID;
    }

    public void connectToServer() {
        try {
            this.tcpSocket = new Socket(serverAddress, tcpPort);
            this.udpSocket = new DatagramSocket();
            this.tcpListener = new TcpListener(tcpSocket);
            this.udpListener = new UdpListener(udpSocket);
            tcpListener.subscribe(this);
            udpListener.subscribe(this);
            this.serverSessionHandler = new ServerSessionHandler(tcpSocket, udpSocket, serverAddress, udpPort);
            this.peerConnectionHandler = new PeerConnectionHandler(udpSocket);
            //TODO start miner and create memPool
            //TODO handle adding transaction to memPool
        } catch (IOException e) {
            System.out.println("Error while creating sockets: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public void startNode() {
        tcpListener.start();
        udpListener.start();
    }

    public void registerNode() {
        serverSessionHandler.registerNode(ID);
    }

    public void requestBroadcast() {
        serverSessionHandler.requestSession(ID);
    }

    @Override
    public void update(Event event) {
        Message message;
        try {
            message = new Gson().fromJson(new String(event.getData()), Message.class);
        } catch (JsonSyntaxException e) {
            System.err.println("Error while parsing message content");
            return;
        }

        switch (message.getType()) {
            case ID:
                //TODO add validation
                this.ID = message.getID();
//                this.registerNode();
                break;
            //TODO add validation everywhere
            case NODE_LIST:
                this.peerConnectionHandler.broadcastDataToPeers(message.parsePeerList(), this.ID);
                break;
            case OPEN_REQUEST:
                this.peerConnectionHandler.openPort(message.parsePeerInfo(), this.ID);
                break;
            case DATA:
                System.out.println("RECEIVED DATA:" + new String(message.getData()));
        }
    }
}
