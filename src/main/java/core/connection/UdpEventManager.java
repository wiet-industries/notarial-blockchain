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
            try{
                this.listenForUdpPackets();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void listenForUdpPackets () throws IOException {
        DatagramPacket receivedDatagram = new DatagramPacket(new byte[1024], 1024);
        datagramSocket.receive(receivedDatagram);
        System.out.println("Received upd packet from: " + receivedDatagram.getAddress() + ":" + receivedDatagram.getPort() + " with data: \n" + new String(receivedDatagram.getData()));
        Message payload = this.createPayload(receivedDatagram);
        Event event = this.createEvent(payload);
        this.notify(event);
    }

    private Message createPayload(DatagramPacket receivedDatagram) throws IOException {
        return new Message(new String(receivedDatagram.getData(), receivedDatagram.getOffset(), receivedDatagram.getLength()), null);
    }

    private Event createEvent(Message payload) {
        return new Event(payload);
    }
}
