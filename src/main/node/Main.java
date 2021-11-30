package main.node;

import main.node.Model.Config;
import org.json.JSONObject;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        try {
            Node node = new Node(Config.TCP_PORT, Config.UDP_PORT, InetAddress.getByName(Config.IP));
            node.startNode();
            //node.registerNode();
            Scanner scanner = new Scanner(System.in);
            while(scanner.nextLine() != null) {
                System.out.println("Sending broadcast request");
                node.requestBroadcast();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


    }


}
