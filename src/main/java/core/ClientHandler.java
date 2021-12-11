package core;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import core.connection.EventManager;
import core.models.Event;
import core.models.SocketPayload;
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
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new BufferedOutputStream(socket.getOutputStream());
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
                this.listenForTcpPackets();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void listenForTcpPackets() throws IOException {
        String message = this.input.readLine();
        System.out.println("Message: " + message + "from node: " + ID);
        SocketPayload payload = new SocketPayload(message, socket.getPort(), socket.getInetAddress(), socket);
        Event event = new Event(payload);
        this.notify(event);
    }

    public JsonObject getClientConnectionDataAsJson() {
        JsonObject clientRecord = new JsonObject();
        clientRecord.addProperty("ipAddress", this.IP.toString().substring(1));
        clientRecord.addProperty("port", this.updPort);
        return clientRecord;
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

