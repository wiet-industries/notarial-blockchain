package main.core;

import java.net.InetAddress;

public class SocketPayload {
    private final String message;
    private final int port;
    private final InetAddress address;

    public SocketPayload(String message, int port, InetAddress address) {
        this.message = message;
        this.port = port;
        this.address = address;
    }

    public String getMessage() {
        return this.message;
    }

    public int getPort() {
        return this.port;
    }

    public InetAddress getAddress() {
        return this.address;
    }
}
