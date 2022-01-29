package rest;

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
        return new Node(Config.TCP_PORT, Config.UDP_PORT, InetAddress.getByName(Config.IP));
    }

    @Bean
    TransactionAdapter createAdapter() throws UnknownHostException {
        return new TransactionAdapter();
    }
}
