package main.node;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) {
        try {
            Node node = new Node(Config.TCP_PORT, Config.UDP_PORT, InetAddress.getByName(Config.IP));
            node.startNode();
            node.registerNode();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


    }


}
