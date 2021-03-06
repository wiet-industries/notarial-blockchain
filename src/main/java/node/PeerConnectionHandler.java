package node;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import node.Model.Message;
import node.Model.MessageType;
import node.Model.Peer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public class PeerConnectionHandler {

    private final DatagramSocket udpSocket;

    public PeerConnectionHandler(DatagramSocket udpSocket) {
        this.udpSocket = udpSocket;
    }

    public void broadcastDataToPeers(List<Peer> peers, int id, JsonElement content) {
        //TODO Here I want to broadcast my blockchain
        Message message = new Message(MessageType.BLOCKCHAIN_DATA, content, id);
//        Message message = new Message(MessageType.DATA, new Gson().toJsonTree("TEST-DATA"), id);
        byte[] data = message.getData();
        peers.forEach(peer -> {
            openPort(peer, id);
            DatagramPacket datagramPacket = null;
            try {
                datagramPacket = new DatagramPacket(data, data.length, InetAddress.getByName(peer.getIpAddress()), peer.getPort());
            } catch (UnknownHostException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Sending data to Peer IP: " + peer.getIpAddress() + ", PORT: " + peer.getPort());
            try {
                //TODO
                for (int i = 0; i < 500; i++) {
                        udpSocket.send(datagramPacket);
                }
            } catch (IOException e) {
                System.out.println("Error sending datagram\n" +
                        "address: " + peer.getIpAddress() + ':' + peer.getPort() + "\n" +
                        "data: " + Arrays.toString(data));
                e.printStackTrace();
            }
        });
    }

    public void openPort(Peer peer, int id) {
        Message message = new Message(MessageType.SESSION_DATA, new Gson().toJsonTree("TRASH-DATA"), id);
        byte[] data = message.getData();
        DatagramPacket datagramPacket = null;
        try {
            datagramPacket = new DatagramPacket(data, data.length, InetAddress.getByName(peer.getIpAddress()), peer.getPort());
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }
        int TRASH_DATAGRAM_COUNT = 2000;
        System.out.println("Making hole with IP: " + peer.getIpAddress() + ", PORT: " + peer.getPort());
        for (int i = 0; i < TRASH_DATAGRAM_COUNT; i++) {
            try {
                udpSocket.send(datagramPacket);
            } catch (IOException e) {
                System.out.println("Error sending datagram\n" +
                        "address: " + peer.getIpAddress() + ':' + peer.getPort() + "\n" +
                        "data: " + Arrays.toString(data));
                e.printStackTrace();
            }
        }
    }

}
