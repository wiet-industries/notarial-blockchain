package main.node;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;
import java.util.List;

public class PeerConnectionHandler {

    private DatagramSocket udpSocket;

    PeerConnectionHandler(DatagramSocket udpSocket) {
        this.udpSocket = udpSocket;
    }
    void broadcastDataToPeers(List<Peer> peers) {
        byte[] data = "TEST-DATA".getBytes();
        peers.forEach(peer -> {
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length, peer.ipAddress, peer.port);
            try {
                udpSocket.send(datagramPacket);
            } catch (IOException e) {
                System.out.println("Error sending datagram\n" +
                        "address: " + peer.ipAddress + ':' + peer.port + "\n" +
                        "data: " + Arrays.toString(data));
                e.printStackTrace();
            }
        });
    }

    void openPort(Peer peer) {
        byte[] data = "TRASH-DATA".getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length, peer.getIpAddress(), peer.getPort());
        int TRASH_DATAGRAM_COUNT = 10;
        for (int i = 0; i < TRASH_DATAGRAM_COUNT; i++) {
            try {
                udpSocket.send(datagramPacket);
            } catch (IOException e) {
                System.out.println("Error sending datagram\n" +
                        "address: " + peer.ipAddress + ':' + peer.port + "\n" +
                        "data: " + Arrays.toString(data));
                e.printStackTrace();
            }
        }
    }

}
