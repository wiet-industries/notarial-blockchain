package main.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class TcpEventManager extends Thread {
    ServerSocket serverSocket;
    List<Client> clients;

    public TcpEventManager(ServerSocket serverSocket, List<Client> clients) {
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
