import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;

import static java.lang.Thread.sleep;


class CLIENT_CONF {
    static final String IP = "77.55.216.56";
    static final int TCP_PORT = 7000;
    static final int UDP_PORT = 8000;
}

class NodeClient {
    String IP;
    int tcpPort;
    Socket socket;
    BufferedReader input;
    BufferedOutputStream output;

    public NodeClient(String IP, int tcpPort) {
        this.IP = IP;
        this.tcpPort = tcpPort;
    }

    public void start() throws IOException {
        socket = new Socket(InetAddress.getByName(IP), tcpPort);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new BufferedOutputStream(socket.getOutputStream());

        while (true) {
            String response = input.readLine();
            System.out.println("TCP Message from server: " + response);
            try {
                sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Node {
    public static void main(String[] args) throws UnknownHostException, IOException {
        NodeClient nodeClient = new NodeClient(CLIENT_CONF.IP, CLIENT_CONF.TCP_PORT);
        nodeClient.start();
    }
}
