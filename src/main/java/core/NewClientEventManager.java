package core;

import org.json.JSONObject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


// TODO: handle data reciveving
public class NewClientEventManager extends EventManager {
    ServerSocket serverSocket;

    public NewClientEventManager(int port) throws IOException {
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

    private void listenForTcpPackets() throws IOException {
        Socket clientSocket = this.serverSocket.accept();
        JSONObject jo = new JSONObject();
        jo.put("id", "0");
        jo.put("type", "CONNECT");
        jo.put("content","0");
        System.out.println("New client with IP: " + clientSocket.getInetAddress() + ", PORT: " + clientSocket.getPort());
        SocketPayload payload = new SocketPayload(jo.toString(), clientSocket.getPort(), clientSocket.getInetAddress(), clientSocket);
        Event event = new Event(payload);
        this.notify(event);
    }

}
