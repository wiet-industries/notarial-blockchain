package node;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.mongodb.DB;
import database.DBConnection;
import logic.Company;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;
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
    //    private final Miner miner;
//    private final MemPool memPool;
//    private final Blockchain blockchain;
    private final BlockchainProcessingHandler blockchainProcessingHandler;
    private final DB database;
    private UdpListener udpListener;
    private TcpListener tcpListener;
    private ServerSessionHandler serverSessionHandler;
    private PeerConnectionHandler peerConnectionHandler;
    private Socket tcpSocket;
    private DatagramSocket udpSocket;
    private int ID;

    public Node(int tcpPort, int udpPort, InetAddress serverAddress, DB database) {
        this.tcpPort = tcpPort;
        this.udpPort = udpPort;
        this.serverAddress = serverAddress;
        this.database = database;

//        this.blockchain = new Blockchain();

        this.blockchainProcessingHandler = new BlockchainProcessingHandler();
        this.blockchainProcessingHandler.getBlockchain().subscribe(this);

//        this.memPool = new MemPool();
//        this.miner = new Miner(this.memPool, this.blockchain);
//        this.miner.start();
        DBConnection db = new DBConnection();
        //System.out.println(db.getBlockchainJson());
        this.handleBlockchainFromOtherNode(new JsonParser().parse(db.getBlockchainJson()));
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
        } catch (IOException e) {
            System.out.println("Error while creating sockets: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void startNode() {
        this.tcpListener.start();
        this.udpListener.start();
//        this.miner.start();
    }

    public Company getCompanyWithID(int ID) {
        return this.blockchainProcessingHandler.getCompanyWithID(ID);
    }

    public void registerNode() {
        serverSessionHandler.registerNode(ID);
    }

    public void requestBroadcast() {
        serverSessionHandler.requestSession(ID);
    }

    public void disconnect() {
        serverSessionHandler.disconnect(ID);
    }

    @Override
    public void update(Event event) {
        Message message;
        try {
            message = new Gson().fromJson(new String(event.getData()), Message.class);
        } catch (JsonSyntaxException e) {
            System.out.println(new String(event.getData()));
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
                this.peerConnectionHandler.broadcastDataToPeers(message.parsePeerList(), this.ID, this.blockchainProcessingHandler.getBlockchain().getBlockchainAsJsonElement());
                break;
            case OPEN_REQUEST:
                this.peerConnectionHandler.openPort(message.parsePeerInfo(), this.ID);
                break;
            case SESSION_DATA:
                System.out.println("RECEIVED DATA:" + new String(message.getData()));
                break;
            case BLOCKCHAIN_DATA:
                try {
                    System.out.println("Elo" + message.getContent());
                    this.handleBlockchainFromOtherNode(message.getContent());
                } catch (Exception e) {
                    System.err.println("Error while trying to parse received message to Transaction.\n" + e.getMessage());
                }
//                System.out.println("RECEIVED DATA:" + new String(message.getData()));
                break;
            case REQUEST_BROADCAST:
                this.requestBroadcast();
                break;
        }
    }

    public void addTransactionToMemPool(AbstractTransaction transaction) {
        System.out.println("to MemPool: " + transaction.toString());
        this.blockchainProcessingHandler.addTransactionToMemPool(transaction);
//        this.memPool.addTransaction(transaction);
//        // TODO does it work?
//        synchronized (this.miner) {
//            this.miner.notify();
//        }
    }

    private void handleBlockchainFromOtherNode(JsonElement unparsedBlockchain) {
        this.blockchainProcessingHandler.handleBlockchainFromOtherNode(unparsedBlockchain);
//        if (blockchain.size() < this.blockchain.getBlockchain().size()) {
//            return;
//        }
//        Block current = blockchain.get(blockchain.size() - 1);
//        for (int i = blockchain.size() - 2; i >= 0; i--) {
//            Block b = blockchain.get(i);
//            if (b.getHash().equals(current.getPreviousHash())) {
//                current = b;
//            } else {
//                throw new RuntimeException("Blockchain Invalid");
//            }
//        }
//        this.blockchain.setBlockchain(blockchain);
//        System.out.println(this.blockchain.getBlockchain());
//        //sprawdzić poprawność otrzymanego i wybrać dłuższy
    }
}
