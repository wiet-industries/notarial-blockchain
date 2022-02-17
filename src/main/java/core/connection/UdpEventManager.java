package core.connection;

import core.models.Event;
import core.models.Message;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpEventManager extends EventManager {
    DatagramSocket datagramSocket;

    public UdpEventManager(int port) throws IOException {
        this.datagramSocket = new DatagramSocket(port);
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.listenForUdpPackets();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void listenForUdpPackets() throws IOException {
        DatagramPacket receivedDatagram = new DatagramPacket(new byte[32768], 32768);
        datagramSocket.receive(receivedDatagram);
        System.out.println("Received upd packet from: " + receivedDatagram.getAddress() + ":" + receivedDatagram.getPort() + " with data: \n" + new String(receivedDatagram.getData()));
        Message message = this.createPayload(receivedDatagram);
        Event event = this.createEvent(message);
        this.notify(event);
    }

    private Message createPayload(DatagramPacket receivedDatagram) throws IOException {
        return new Message(new String(receivedDatagram.getData(), receivedDatagram.getOffset(), receivedDatagram.getLength()), receivedDatagram.getAddress(), receivedDatagram.getPort(), null);
    }

    private Event createEvent(Message payload) {
        return new Event(payload);
    }
}
