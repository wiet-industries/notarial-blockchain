package core;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class Client extends EventManager {
    Socket socket;
    BufferedReader input;
    BufferedOutputStream output;
    int ID;
    InetAddress IP;
    int updPort;
    List<Client> clients;

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

    public Client(Socket socket, int ID, List<Client> clients) throws IOException {
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
                String response = input.readLine();
                System.out.println("Message: " + response + "from node: " + ID);
                SocketPayload payload = new SocketPayload(response, socket.getPort(), socket.getInetAddress(), socket);
                Event event = new Event(payload);
                this.notify(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

