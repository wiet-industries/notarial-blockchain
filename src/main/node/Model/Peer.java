package main.node.Model;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Peer {
    private InetAddress ipAddress;
    private int port;


    Peer(InetAddress ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public Peer(String ipAddress, int port) {
        try {
            this.ipAddress = InetAddress.getByName(ipAddress);
        } catch (UnknownHostException e) {
            System.out.println("Couldn't determine ip address of a host" + e.getMessage());
            e.printStackTrace();
        }
        this.port = port;
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public int getPort() {
        return port;
    }
}
