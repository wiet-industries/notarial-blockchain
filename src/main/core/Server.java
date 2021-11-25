import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author user
 */

class CONF {
    static final int TCP_PORT = 7000;
    static final int UDP_PORT = 8000;
}

class ClientData {
    String IP;
    int port;

    public ClientData(String IP, int port) {
        this.IP = IP;
        this.port = port;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}

class Sender {

}


class Client extends Thread {
    Socket socket;
    BufferedReader input;
    BufferedOutputStream output;
    int ID;

    public Client(Socket socket, int ID) throws IOException {
        this.socket = socket;
        this.ID = ID;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new BufferedOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        System.out.println("Client connected. IP: " + socket.getInetAddress() + ", PORT_TCP: " + socket.getPort());
        try {
            output.write(ID);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        while (true) {
//            String line =
//        }
    }
}

class Listener extends Thread {
    ServerSocket serverSocket;
    List<Client> clients;

    public Listener(ServerSocket serverSocket, List<Client> clients) {
        this.serverSocket = serverSocket;
        this.clients = clients;
    }

    @Override
    public void run() {
        while (true) {
            try (Socket clientSocket = serverSocket.accept()) {
                Client newClient = new Client(clientSocket, clients.size()  + 1);
                newClient.start();
                clients.add(newClient);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class ServerInstance {
    final ServerSocket serverSocket;
    final DatagramSocket dgSocket;
    List<Client> clients = new ArrayList<>();
    Listener listener;


    public ServerInstance(int tcpPort, int udpPort) throws IOException {
        serverSocket = new ServerSocket(tcpPort);
        dgSocket = new DatagramSocket(udpPort);
    }

    void startServer() {
        listener = new Listener(serverSocket, clients);
        listener.start();
        System.out.println("Server started with TCP_PORT: " + serverSocket.getLocalPort());
    }

    void stopServer() {
        //TODO
    }
}

public class Server {
    public static void main(String[] args) throws IOException {
        ServerInstance serverInstance = new ServerInstance(CONF.TCP_PORT, CONF.UDP_PORT);
        serverInstance.startServer();
    }
}


class Server2 {

    private int tcpPort = 9000;
    private int udpPort = 9001;

    private BufferedReader inA;
    private BufferedOutputStream outA;

    private BufferedReader inB;
    private BufferedOutputStream outB;

    private ServerSocket serverSocket;
    private Socket clientA, clientB;

    private DatagramPacket receivePacket;

    private boolean readClientA = false;
    private String clientAIp = "";
    private String clientAPort = "";

    private boolean readClientB = false;
    private String clientBIp = "";
    private String clientBPort = "";

    private String udpMsg = "";

    public Server2() {
        try {
            runServer();
        } catch (IOException ex) {
            Logger.getLogger(Server2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Server2(int userTcpPort, int userUdpPort) {
        this.tcpPort = userTcpPort;
        this.udpPort = userUdpPort;
        try {
            runServer();
        } catch (IOException ex) {
            Logger.getLogger(Server2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            new Server2(Integer.parseInt(args[0].trim()), Integer.parseInt(args[1].trim()));
        } else {
            new Server2();
        }

    }

    void runServer() throws IOException {
        //Create Server Socket for accepting Client TCP connections
        serverSocket = new ServerSocket(tcpPort);
        System.out.println("Server started with ports, TCP: " + tcpPort + " UDP: " + udpPort);

        System.out.println("Waiting for Client A");
        //Accept first client connection
        clientA = serverSocket.accept();
        System.out.println("Client 1 connected " + clientA.getInetAddress() + " " + clientA.getPort());

        //Create input and output streams to read/write messages for CLIENT A
        inA = new BufferedReader(new InputStreamReader(clientA.getInputStream()));
        outA = new BufferedOutputStream(clientA.getOutputStream());


        System.out.println("Waiting for Client B");
        //Accept second client connection
        clientB = serverSocket.accept();
        System.out.println("Client 2 connected " + clientB.getInetAddress() + " " + clientB.getPort());

        //Create input and output streams to read/write messages for CLIENT B
        inB = new BufferedReader(new InputStreamReader(clientB.getInputStream()));
        outB = new BufferedOutputStream(clientB.getOutputStream());

        //Create Datagram Socket for udp messages.
        DatagramSocket dgSocket = new DatagramSocket(udpPort);

        //Create Packet to receive UDP messages
        receivePacket = new DatagramPacket(new byte[1024], 1024);
        // receivePacket2 = new DatagramPacket(new byte[1024], 1024);

        /**
         * IMPORTANT *** Create loop to receive initial UDP packets to detect
         * ***
         *
         * *** FIRST CLIENT'S PUBLIC IP AND PORTS ****
         */
        while (readClientA != true) {
            dgSocket.receive(receivePacket);    // Receive UDP Packet

            udpMsg = new String(receivePacket.getData());   //Get Data from UDP packet into a string

            System.out.println(udpMsg);

            clientAIp = "" + receivePacket.getAddress().getHostAddress();     //get public IP of clientA from UDP Packet

            clientAPort = "" + receivePacket.getPort();      //get public UDP PORT of clientA from UDP Packet


            if (udpMsg.trim().equals("one")) {
                readClientA = true;
                System.out.println("Inital UDP message from CLIENT A: " + udpMsg);
            }

            System.out.println("inside while loop1");
        }
        System.out.println("******CLIENT A IP AND PORT DETECTED " + clientAIp + " " + clientAPort + " *****");
        /**
         * *** END OF LOOP FOR CLIENT A ****
         */

        /**
         * IMPORTANT *** Create loop to receive initial UDP packets to detect
         * ***
         *
         * *** SECOND CLIENT'S PUBLIC IP AND PORTS ****
         */
        while (readClientB != true) {
            dgSocket.receive(receivePacket);    // Receive UDP Packet

            udpMsg = new String(receivePacket.getData());   //Get Data from UDP packet into a string

            clientBIp = "" + receivePacket.getAddress().getHostAddress();     //get public IP of clientA from UDP Packet

            clientBPort = "" + receivePacket.getPort();      //get public UDP PORT of clientA from UDP Packet

            System.out.println("in client B loop" + udpMsg);
            if (udpMsg.trim().equals("two")) {
                readClientB = true;
                System.out.println("Initial UDP message from CLIENT B: " + udpMsg);
            }

            System.out.println("inside while loop2");
        }
        System.out.println("******CLIENT B IP AND PORT DETECTED " + clientBIp + " " + clientBPort + " *****");
        /**
         * *** END OF LOOP FOR CLIENT B ****
         */

        /*
         !!!!!!!!!!!CRITICAL PART!!!!!!!!
         The core of hole punching depends on this part.
         The exchange of public IP and port between the clients takes place here.
         */

        System.out.println("***** Exchanging public IP and port between the clients *****");
        while (true) {
            String string = clientAIp + "~~" + clientAPort + "~~" + clientBIp + "~~" + clientBPort;
            outA.write(string.getBytes());      //SENDING CLIENT B's public IP & PORT TO CLIENT A
            outA.write('\n');
            outA.flush();

            String string1 = clientBIp + "~~" + clientBPort + "~~" + clientAIp + "~~" + clientAPort;
            outB.write(string1.getBytes());     //SENDING CLIENT A's public IP & PORT TO CLIENT B
            outB.write('\n');
            outB.flush();
        }

    }

}