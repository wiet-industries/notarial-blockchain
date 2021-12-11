package core;

import com.google.gson.JsonObject;
import core.connection.EventManager;
import core.models.Event;
import core.models.Message;
import core.models.MessageContent;
import core.models.MessageType;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.Objects;

public class ClientHandler extends EventManager {
    private Socket socket;
    private BufferedReader input;
    private BufferedOutputStream output;
    private int ID;
    private InetAddress IP;
    private int updPort;

    public ClientHandler(Socket socket, int ID, List<ClientHandler> clients) throws IOException {
        this.socket = socket;
        this.ID = ID;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new BufferedOutputStream(socket.getOutputStream());
        this.clients = clients;

    }

    private List<ClientHandler> clients;

    public BufferedOutputStream getOutput() {
        return output;
    }

    public Socket getSocket() {
        return socket;
    }

    public int getID() {
        return ID;
    }

    public InetAddress getIP() {
        return IP;
    }

    public void setIP(InetAddress IP) {
        this.IP = IP;
    }

    public int getUpdPort() {
        return updPort;
    }

    public void setUpdPort(int updPort) {
        this.updPort = updPort;
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.listenForClientDisconnect();
                this.listenForTcpPackets();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void listenForClientDisconnect() throws IOException{
        if (!this.isClientConnected()) {
            Message message = new Message(this.createDisconnectMessage().toJson(), this.socket);
            Event event = new Event(message);
            this.notify(event);
        }
    }

    private void listenForTcpPackets() throws IOException {
        String message = this.input.readLine();
        System.out.println("Message: " + message + "from node: " + ID);
        Message payload = new Message(message, socket);
        Event event = new Event(payload);
        this.notify(event);
    }

    public JsonObject getClientConnectionDataAsJson() {
        JsonObject clientRecord = new JsonObject();
        clientRecord.addProperty("ipAddress", this.IP.toString().substring(1));
        clientRecord.addProperty("port", this.updPort);
        return clientRecord;
    }

    private boolean isClientConnected() throws IOException {
        return this.input.read() != -1;
    }

    private MessageContent createDisconnectMessage() {
        return new MessageContent(MessageType.DISCONNECT, new JsonObject(), this.ID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientHandler that = (ClientHandler) o;
        return ID == that.ID && updPort == that.updPort && IP.equals(that.IP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, IP, updPort);
    }
}

