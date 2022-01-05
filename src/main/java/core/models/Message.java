package core.models;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Message {
    private final MessageContent messageContent;
    private final Socket socket;
    private final InetAddress ip;
    private final int port;

    public Message(String message, InetAddress ip, int port, Socket socket) throws IOException {
        this.messageContent = new Gson().fromJson(message, MessageContent.class);
        this.socket = socket;
        this.ip = ip;
        this.port = port;
    }

    public MessageContent getMessageContent() {
        return this.messageContent;
    }

    public int getPort() {
        return this.port;
    }

    public InetAddress getAddress() {
        return this.ip;
    }

    public Socket getSocket () {
        return this.socket;
    }
}
