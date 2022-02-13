package node.Listeners;


import node.Model.Event;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class UdpListener extends EventManager {
    DatagramSocket socket;

    public UdpListener(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            this.listenForUdpPackets();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void listenForUdpPackets() throws IOException {
        DatagramPacket datagram = new DatagramPacket(new byte[32768], 32768);
        while (true) {
            socket.receive(datagram);
            //System.out.println("Received upd packet from: " + datagram.getAddress() + ":" + datagram.getPort() + " with data: " + new String(datagram.getData()));
            System.out.println(new String(Arrays.copyOf(datagram.getData(), datagram.getLength())));
            Event event = new Event(Arrays.copyOf(datagram.getData(), datagram.getLength()));
            this.notify(event);
        }
    }

}
