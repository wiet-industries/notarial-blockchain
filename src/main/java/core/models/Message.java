package core.models;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Message {
    private final MessageContent messageContent;
    private final Socket socket;

    public Message(String message, Socket socket) throws IOException {
        this.messageContent = new Gson().fromJson(message, MessageContent.class);
        this.socket = socket;
    }

    public MessageContent getMessageContent() {
        return this.messageContent;
    }

    public int getPort() {
        return this.socket.getPort();
    }

    public InetAddress getAddress() {
        return this.socket.getInetAddress();
    }

    public Socket getSocket () {
        return this.socket;
    }
}
