package main.core;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Server serverInstance = new Server(Config.TCP_PORT, Config.UDP_PORT);
        serverInstance.startServer();
    }
}
