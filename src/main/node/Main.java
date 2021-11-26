import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


class CLIENT_CONF {
    static final String IP = "77.55.216.56";
    static final int TCP_PORT = 7000;
    static final int UDP_PORT = 8000;
}

class Node {
    String ip;
    int port;

    Node(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp(){
        return ip;
    }

    public int getPort() {
        return port;
    }
}

class TcpListener extends Thread {
    BufferedReader input;
    NodeClient nodeClient;

    TcpListener(BufferedReader inputStream, NodeClient nodeClient) {
        this.input = inputStream;
        this.nodeClient = nodeClient;
    }

    public void listen() throws IOException {
        String data = null;
        while (true) {
            data = input.readLine();
            System.out.println("TCP RECEIVED:" + data);
            if (data != null) {
                if (data.split("~")[0].equals("NODE_LIST")) {
                    nodeClient.broadcast(data);
                }
                if (data.split("~")[0].equals("MAKE_HOLE")) {
                    nodeClient.punchHole(data);
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            listen();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class UdpListener extends Thread {
    DatagramSocket udpSocket;
    NodeClient nodeClient;

    UdpListener(DatagramSocket udpSocket, NodeClient nodeClient) {
        this.udpSocket = udpSocket;
        this.nodeClient = nodeClient;
    }

    public void listen() throws IOException {
        String data = null;
        while (true) {
            DatagramPacket datagramPacket = new DatagramPacket(new byte[20], 20);
            udpSocket.receive(datagramPacket);
            data = new String(datagramPacket.getData());
            System.out.println("UDP RECEIVED:" + data);
        }
    }

    @Override
    public void run() {
        try {
            listen();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}




class NodeClient {
    String IP;
    int tcpPort;
    int udpPort;
    int ID;
    Socket tcpSocket;
    TcpListener tcpListener;
    DatagramSocket udpSocket;
    BufferedReader input;
    BufferedOutputStream output;

    public NodeClient(String IP, int tcpPort, int udpPort) {
        this.IP = IP;
        this.tcpPort = tcpPort;
        this.udpPort = udpPort;
    }

    public void start() throws IOException {
        tcpSocket = new Socket(InetAddress.getByName(IP), tcpPort);
        input = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));
        output = new BufferedOutputStream(tcpSocket.getOutputStream());

        String response = null;
        while (response == null) response = input.readLine();
        this.ID = Integer.parseInt(response);
        System.out.println("TCP Message from server: " + response);

        tcpListener = new TcpListener(input, this);
        tcpListener.start();
    }

    public void initializePeerSession() throws IOException {
        String initializeInfo = "BROADCAST~" + ID + "\n";
        System.out.println("BEFORE");
        output.write(initializeInfo.getBytes());
        output.flush();
        System.out.println("AFTER");
    }

    private List<Node> parseNodesInfo(String data) {
        List<Node> nodes = new ArrayList<>();
        String[] nodesInfo = data.split("~");
        Arrays.stream(nodesInfo).skip(1).forEach(nodeInfo -> {
            String[] ipPortPair = nodeInfo.split("-");
            nodes.add(new Node(ipPortPair[0], Integer.parseInt(ipPortPair[1])));
        });
        return nodes;
    }

    public void broadcast(String nodesInfo) {
        List<Node> nodes = parseNodesInfo(nodesInfo);
        String data = "WITAM\n";
        while(true){
            nodes.forEach(node -> {
                DatagramPacket datagramPacket = null;
                try {
                    datagramPacket = new DatagramPacket(data.getBytes(), data.getBytes().length, InetAddress.getByName(node.getIp()), node.getPort());
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                try {
                    udpSocket.send(datagramPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void punchHole(String nodesInfo) throws IOException {
        Node node = parseNodesInfo(nodesInfo).get(0);


        String trashData = "aaaa"+ID+'\n';
        for(int i = 0; i < 20;i++) {
            DatagramPacket datagramPacket = new DatagramPacket(trashData.getBytes(), trashData.length(), InetAddress.getByName(node.getIp()), node.getPort());
            udpSocket.send(datagramPacket);
        }
        UdpListener udpListener = new UdpListener(udpSocket, this);
        udpListener.start();
    }

    public void sendUdpInfo() throws IOException {
        udpSocket = new DatagramSocket();
        String data = "REGISTER~" + ID;
        byte[] bytes = data.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(IP), udpPort);
        udpSocket.send(datagramPacket);
    }
}

class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        NodeClient nodeClient = new NodeClient(CLIENT_CONF.IP, CLIENT_CONF.TCP_PORT, CLIENT_CONF.UDP_PORT);
        nodeClient.start();
        nodeClient.sendUdpInfo();
        Scanner sc = new Scanner(System.in);
        while(true) {
            sc.nextLine();
            nodeClient.initializePeerSession();
        }
    }
}