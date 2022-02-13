package rest;

import com.mongodb.DB;
import database.DBConnection;
import logic.TransactionAdapter;
import node.Model.Config;
import node.Node;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.InetAddress;
import java.net.UnknownHostException;


@SpringBootApplication
public class RestApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    @Bean
    Node createNode() throws UnknownHostException {
        DBConnection.startDBConnection("mongodb://127.0.0.1:27017");
        //DB database = DBConnection.getDatabase("blockchain-local-db");
        // after end close connection
        return new Node(Config.TCP_PORT, Config.UDP_PORT, InetAddress.getByName(Config.IP), null);
    }

    @Bean
    TransactionAdapter createAdapter() throws UnknownHostException {
        return new TransactionAdapter();
    }
}
