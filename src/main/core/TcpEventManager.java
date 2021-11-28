package main.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;


// TODO: handle data reciveving
public class TcpEventManager extends EventManager{
    ServerSocket serverSocket;

    public TcpEventManager(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.listenForTcpPackets();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void listenForTcpPackets () throws IOException {
        Socket clientSocket = this.serverSocket.accept();
        System.out.println("Tcp packet accepted from: " + clientSocket.getInetAddress());
        BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String responseData = inputBuffer.readLine();
        System.out.println("Got data from: " + clientSocket.getInetAddress());
        System.out.println(responseData);
        SocketPayload payload = this.createPayload(clientSocket, responseData);
        Event event = this.createEvent(payload);
        this.notify(event);
    }

    private SocketPayload createPayload(Socket clientSocket, String responseData) {
        return new SocketPayload(responseData, clientSocket.getPort(), clientSocket.getInetAddress(), PacketType.TCP, clientSocket);
    }

    private Event createEvent(SocketPayload payload) {
        return new Event(payload);
    }
}
