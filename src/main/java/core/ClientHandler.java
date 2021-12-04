package core;

import com.google.gson.JsonObject;
import core.connection.EventManager;
import core.models.Event;
import core.models.SocketPayload;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends EventManager {
    Socket socket;
    BufferedReader input;
    BufferedOutputStream output;
    int ID;
    InetAddress IP;
    int updPort;
    List<ClientHandler> clients;

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

    public ClientHandler(Socket socket, int ID, List<ClientHandler> clients) throws IOException {
        this.socket = socket;
        this.ID = ID;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new BufferedOutputStream(socket.getOutputStream());
        this.clients = clients;
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
        String response = this.input.readLine();
        System.out.println("Message: " + response + "from node: " + ID);
        SocketPayload payload = new SocketPayload(response, socket.getPort(), socket.getInetAddress(), socket);
        Event event = new Event(payload);
        this.notify(event);
    }

    public JsonObject getClientDataAsJson() {
        JsonObject clientRecord = new JsonObject();
        clientRecord.addProperty("ip", this.IP.toString().substring(1));
        clientRecord.addProperty("port", this.updPort);
        return clientRecord;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()){
            return false;
        }
        ClientHandler other = (ClientHandler) obj;
        return this.ID == other.getID();
    }
}

