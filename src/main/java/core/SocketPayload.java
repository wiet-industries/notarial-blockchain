package core;

import java.net.InetAddress;
import java.net.Socket;

public class SocketPayload {
    private final PayloadMessage payloadMessage;
    private final int port;
    private final InetAddress address;
    private final Socket socket;

    public SocketPayload(String message, int port, InetAddress address, Socket socket) {
        this.payloadMessage = new PayloadMessage(message);
        this.port = port;
        this.address = address;
        this.socket = socket;
    }

    public PayloadMessage getPayloadMessage() {
        return this.payloadMessage;
    }

    public int getPort() {
        return this.port;
    }

    public InetAddress getAddress() {
        return this.address;
    }

    public Socket getSocket () {
        return this.socket;
    }
}
