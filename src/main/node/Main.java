package main.node;

import main.node.Model.Config;
import org.json.JSONObject;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<>();
//        Address address1 = new Address("1.1.1.1", "7000");
//        Address address2 = new Address("2.2.2.2", "8000");
//        ArrayList <String> addresses = new ArrayList<String>();
//        addresses.add("45.454.2.5:3545");
//        addresses.add("45.454.2.5:3545");
//        //Collections addressesCol = new Collections(addresses);
//        map.put("name", "jon doe");
//        map.put("age", "22");
//        map.put("city", "chicago");
//        //map.put("adresses", new JSONArray(addresses));
//        JSONObject jo = new JSONObject(map);
//
//        System.out.println(jo);
//
//        JSONObject jo2 = new JSONObject();
//        jo2.put("name", "jon doe");
//        jo2.put("age", "22");
//        jo2.put("city", "chicago");
//        jo2.put("adresses", addresses);
//        System.out.println(addresses);
//        System.out.println(jo2);

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
