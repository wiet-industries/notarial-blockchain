package main.core;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.List;

public class UdpEventManager extends EventManager {
    DatagramSocket datagramSocket;
    List<Client> clients;

    public UdpEventManager(int port) throws IOException {
        this.datagramSocket = new DatagramSocket(port);
    }

    @Override
    public void run() {
        while (true) {
            try{
                this.listenForUdpPackets();
            } catch (Exception e) {
                e.printStackTrace();
            }
            DatagramPacket receiveDatagram = new DatagramPacket(new byte[1024], 1024);
            try {
                datagramSocket.receive(receiveDatagram);
                String message = new String(receiveDatagram.getData());
                System.out.println("UPD Message: " + message + ", From IP: " + receiveDatagram.getAddress() + ", PORT: " + receiveDatagram.getPort());
                String[] chunks = message.trim().split("~");
                if (chunks.length == 0) {
                    continue;
                }
                switch (chunks[0]) {
                    case "REGISTER":
                        for (Client client : clients) {
                            if (client.getID() == Integer.parseInt(chunks[1])) {
                                client.setIP(receiveDatagram.getAddress());
                                client.setUpdPort(receiveDatagram.getPort());
                            }
                        }
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void listenForUdpPackets () throws IOException {
        DatagramPacket receivedDatagram = new DatagramPacket(new byte[1024], 1024);
        datagramSocket.receive(receivedDatagram);
        SocketPayload payload = this.createPayload(receivedDatagram);
        Event event = this.createEvent(payload);
        this.notify(event);
    }

    private SocketPayload createPayload(DatagramPacket receivedDatagram) {
        return new SocketPayload(new String(receivedDatagram.getData()), receivedDatagram.getPort(), receivedDatagram.getAddress());
    }

    private Event createEvent(SocketPayload payload) {
        return new Event(payload);
    }
}
