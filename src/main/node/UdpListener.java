package main.node;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpListener extends EventManager {
    DatagramSocket socket;

    public UdpListener(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            this.listen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void listen() throws IOException {
        while (true) {
            DatagramPacket datagram = new DatagramPacket(new byte[1024], 1024);
            socket.receive(datagram);
            System.out.println("Received upd packet from: " + datagram.getAddress() + ":" + datagram.getPort() + " with data: \n" + datagram.getData());
            Event event = new Event(datagram.getData());
            this.notify(event);
        }
    }

}
