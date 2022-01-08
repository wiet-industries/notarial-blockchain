package node;

import org.springframework.boot.SpringApplication;
import rest.RestApplication;

public class Main {
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
//        try {
//            Node node = new Node(Config.TCP_PORT, Config.UDP_PORT, InetAddress.getByName(Config.IP));
////            node.startNode();
////            //node.registerNode();
////            Scanner scanner = new Scanner(System.in);
////            while(scanner.nextLine() != null) {
////                System.out.println("Sending broadcast request");
////                node.requestBroadcast();
////            }
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
    }


}
