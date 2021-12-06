package core.connection;

import com.google.gson.JsonElement;
import core.connection.EventManager;
import core.models.Event;
import core.models.MessageType;
import core.models.PayloadMessage;
import core.models.SocketPayload;
import com.google.gson.JsonObject;
import node.Model.Message;

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
        System.out.println("New client with IP: " + clientSocket.getInetAddress() + ", PORT: " + clientSocket.getPort());
        SocketPayload payload = new SocketPayload(this.createConnectionMessage().toString(), clientSocket.getPort(), clientSocket.getInetAddress(), clientSocket);
        Event event = new Event(payload);
        this.notify(event);
    }

    private PayloadMessage createConnectionMessage() {
        PayloadMessage message = new PayloadMessage(MessageType.CONNECT, new JsonObject(), -1);
        return message;
    }

}
