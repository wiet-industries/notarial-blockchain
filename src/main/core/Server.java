import javax.xml.crypto.Data;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.BufferUnderflowException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    InetAddress IP;
    int updPort;
    List<Client> clients;

    public BufferedOutputStream getOutput() {
        return output;
    }

    public Socket getSocket() {
        return socket;
    }

    public int getID() {
        return ID;
    }

    public InetAddress getIP() {
        return IP;
    }

    public void setIP(InetAddress IP) {
        this.IP = IP;
    }

    public int getUpdPort() {
        return updPort;
    }

    public void setUpdPort(int updPort) {
        this.updPort = updPort;
    }

    public Client(Socket socket, int ID, List<Client> clients) throws IOException {
        this.socket = socket;
        this.ID = ID;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new BufferedOutputStream(socket.getOutputStream());
        this.clients = clients;
    }

    @Override
    public void run() {
        System.out.println("Client connected. IP: " + socket.getInetAddress() + ", PORT_TCP: " + socket.getPort());
        try {
            output.write(String.valueOf(ID).getBytes());
            output.write('\n');
            output.flush();
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        while (true) {

            String response = null;
            try {
                response = input.readLine();
                System.out.println(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("TCP Message from node: " + response);

            System.out.println("Message: " + response + ", From IP: " + socket.getInetAddress().toString() + ", PORT: " + socket.getPort());
            String[] chunks = response.trim().split("~");
            if(chunks.length > 0 && chunks[0].equals("BROADCAST")) {
                String toBroadcaster = "NODE_LIST";//TODO not count clients without UDP PORT

                //TODO rewrite to stream
                Client broadcaster = null;
                for (Client client : clients) {
                    if (client.getID() == Integer.parseInt(chunks[1])) {
                        broadcaster = client;
                    }
                }
                String toNodes = "MAKE_HOLE~" + broadcaster.getIP().toString().substring(1) + "-" + broadcaster.getUpdPort();
                BufferedOutputStream bout = broadcaster.getOutput();
                for (Client client : clients) {
                    if (client == null || client.getID() == Integer.parseInt(chunks[1])) {
                        continue;
                    }
                    BufferedOutputStream out = client.getOutput();
                    try {
                        out.write(toNodes.getBytes(StandardCharsets.UTF_8)); // the same socket for each client
                        out.write('\n');
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    toBroadcaster = toBroadcaster + "~" + client.getIP().toString().substring(1) + "-" + client.getUpdPort();
                }

                try {
                    bout.write(toBroadcaster.getBytes(StandardCharsets.UTF_8)); // the same socket for each client
                    bout.write('\n');
                    bout.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;
            }
        }
    }
}

class TcpListener1 extends Thread {
    ServerSocket serverSocket;
    List<Client> clients;

    public TcpListener1(ServerSocket serverSocket, List<Client> clients) {
        this.serverSocket = serverSocket;
        this.clients = clients;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                Client newClient = new Client(clientSocket, clients.size() + 1, clients);
                newClient.start();
                clients.add(newClient);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class UpdListener1 extends Thread {
    DatagramSocket datagramSocket;
    List<Client> clients;

    public UpdListener1(DatagramSocket datagramSocket, List<Client> clients) {
        this.datagramSocket = datagramSocket;
        this.clients = clients;
    }

    @Override
    public void run() {
        while (true) {
            DatagramPacket receiveDatagram = new DatagramPacket(new byte[1024], 1024);
            try {
                datagramSocket.receive(receiveDatagram);
                String message = new String(receiveDatagram.getData());
                System.out.println("UPD Message: " + message + ", From IP: " + receiveDatagram.getAddress() + ", PORT: " + receiveDatagram.getPort());
                String[] chunks = message.trim().split("~");
                if (chunks.length == 0) {
                    continue;
                }
                switch (chunks[0]) {
                    case "REGISTER":
                        for (Client client : clients) {
                            if (client.getID() == Integer.parseInt(chunks[1])) {
                                client.setIP(receiveDatagram.getAddress());
                                client.setUpdPort(receiveDatagram.getPort());
                            }
                        }
                        break;
//                    case "BROADCAST":
//                        String toBroadcaster = "NODE_LIST~" + (clients.size() - 1); //TODO not count clients without UDP PORT
//                        String toNodes = "MAKE_HOLE~" + receiveDatagram.getAddress().toString() + "-" + receiveDatagram.getPort();
//                        //TODO rewrite to stream
//                        Client broadcaster = null;
//                        for (Client client : clients) {
//                            if (client.getID() == Integer.parseInt(chunks[1])) {
//                                broadcaster = client;
//                            }
//                        }
//                        BufferedOutputStream out = broadcaster.getOutput();
//                        for (Client client : clients) {
//                            out.write(toNodes.getBytes(StandardCharsets.UTF_8)); // the same socket for each client
//                            out.write('\n');
//                            out.flush();
//                            toBroadcaster = toBroadcaster + "~" + client.getIP() + "-" + client.getUpdPort();
//                        }
//
//                        out.write(toBroadcaster.getBytes(StandardCharsets.UTF_8)); // the same socket for each client
//                        out.write('\n');
//                        out.flush();
//
//                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


//
//ID~BROADCAST
//REGISTER~ID
//CNT_NODE~IP_PORT~IP_PORT~


class ServerInstance {
    final ServerSocket serverSocket;
    final DatagramSocket datagramSocket;
    List<Client> clients = Collections.synchronizedList(new ArrayList<>());
    TcpListener1 tcpListener;
    UpdListener1 updListener;


    public ServerInstance(int tcpPort, int udpPort) throws IOException {
        serverSocket = new ServerSocket(tcpPort);
        datagramSocket = new DatagramSocket(udpPort);
    }

    void startServer() {
        tcpListener = new TcpListener1(serverSocket, clients);
        tcpListener.start();
        System.out.println("Server started with TCP_PORT: " + serverSocket.getLocalPort());
        updListener = new UpdListener1(datagramSocket, clients);
        updListener.start();
        System.out.println("Server started with UPD_PORT: " + datagramSocket.getLocalPort());
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


