package node;

import com.google.gson.Gson;

import node.Model.Message;
import node.Model.MessageType;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class ServerSessionHandler {


    Socket tcpSocket;

    DatagramSocket udpSocket;
    InetAddress serverAddress;
    int udpPort;


    public ServerSessionHandler(Socket tcpSocket, DatagramSocket udpSocket, InetAddress serverAddress, int udpPort) {
        this.serverAddress = serverAddress;
        this.udpPort = udpPort;
        this.udpSocket = udpSocket;
        this.tcpSocket = tcpSocket;
    }

    void registerNode(int id) {
        Message message = new Message(MessageType.REGISTER, new Gson().toJsonTree(Integer.toString(id)), id);
        byte[] data = message.getData();
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length, serverAddress, udpPort);
        try {
            // maybe more udp packets
            udpSocket.send(datagramPacket);
        } catch (IOException e) {
            System.out.println("Couldn't register node: " + e.getMessage());
            e.printStackTrace();
        }
    }

    void requestSession(int id) {
        Message message = new Message(MessageType.BROADCAST, new Gson().toJsonTree(Integer.toString(id)), id);
        try {
            BufferedOutputStream tcpOutput = new BufferedOutputStream(tcpSocket.getOutputStream());
            tcpOutput.write(message.getData());
            tcpOutput.flush();
        } catch (IOException e) {
            System.out.println("Error requesting session: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
