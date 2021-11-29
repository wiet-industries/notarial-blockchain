package main.node;

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
        while (true) {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String data = input.readLine();
            System.out.println("Got data from: " + socket.getInetAddress());
            System.out.println(socket);
            Event event = new Event(data.getBytes());
            this.notify(event);
        }
    }
}
