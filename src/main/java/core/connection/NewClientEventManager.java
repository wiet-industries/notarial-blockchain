package core.connection;

import core.models.Event;
import core.models.MessageType;
import core.models.MessageContent;
import core.models.Message;
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
                this.listenForClientConnections();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void listenForClientConnections() throws IOException {
        Socket clientSocket = this.serverSocket.accept();
        System.out.println("New client with IP: " + clientSocket.getInetAddress() + ", PORT: " + clientSocket.getPort());
        Message payload = new Message(this.createConnectionMessage().toJson(), clientSocket);
        Event event = new Event(payload);
        this.notify(event);
    }

    private MessageContent createConnectionMessage() {
        MessageContent messageContent = new MessageContent(MessageType.CONNECT, new JsonObject());
        return messageContent;
    }

}
