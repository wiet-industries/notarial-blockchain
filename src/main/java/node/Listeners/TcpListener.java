package node.Listeners;


import node.Model.Event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class TcpListener extends EventManager {
    Socket socket;

    public TcpListener(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            this.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void listen() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while (true) {
            String data = input.readLine();
            System.out.println("Message: " + data + " from: IP: " + socket.getInetAddress() + ", PORT: " + socket.getPort());
            Event event = new Event(data.getBytes());
            this.notify(event);
        }
    }
}
