package core.connection;

import core.connection.EventManager;
import core.models.Event;
import core.models.MessageType;
import core.models.SocketPayload;
import com.google.gson.JsonObject;

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
                this.listenForTcpConnections();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void listenForTcpConnections() throws IOException {
        Socket clientSocket = this.serverSocket.accept();
        JsonObject message = this.createConnectionMessage();
        System.out.println("New client with IP: " + clientSocket.getInetAddress() + ", PORT: " + clientSocket.getPort());
        SocketPayload payload = new SocketPayload(message.toString(), clientSocket.getPort(), clientSocket.getInetAddress(), clientSocket);
        Event event = new Event(payload);
        this.notify(event);
    }

    private JsonObject createConnectionMessage() {
        JsonObject message = new JsonObject();
        message.addProperty("id", -1);
        message.addProperty("type", MessageType.CONNECT.toString());
        message.addProperty("content", "");
        return message;
    }

}
