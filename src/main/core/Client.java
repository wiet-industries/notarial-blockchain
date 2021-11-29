package main.core;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Client extends EventManager {
    Socket socket;
    BufferedReader input;
    BufferedOutputStream output;
    int ID;
    InetAddress IP;
    int updPort;
    List<Client> clients;

    public BufferedOutputStream getOutput() {
        return output;
    }

    public Socket getSocket() {
        return socket;
    }

    public int getID() {
        return ID;
    }

    public InetAddress getIP() {
        return IP;
    }

    public void setIP(InetAddress IP) {
        this.IP = IP;
    }

    public int getUpdPort() {
        return updPort;
    }

    public void setUpdPort(int updPort) {
        this.updPort = updPort;
    }

    public Client(Socket socket, int ID, List<Client> clients) throws IOException {
        this.socket = socket;
        this.ID = ID;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new BufferedOutputStream(socket.getOutputStream());
        this.clients = clients;
    }

    @Override
    public void run() {
      //  System.out.println("Client connected. IP: " + socket.getInetAddress() + ", PORT_TCP: " + socket.getPort());
//        try {
////            output.write(String.valueOf(ID).getBytes());
////            output.write('\n');
////            output.flush();
//        } catch (IOException e) {
//            System.out.println(e);
//            e.printStackTrace();
//        }
        while (true) {
            String response = null;
            try {
                response = input.readLine();
                System.out.println(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("TCP Message from node: " + response);

            SocketPayload payload = new SocketPayload(response, socket.getPort(), socket.getInetAddress(), PacketType.TCP, socket);
            Event event = new Event(payload);
            this.notify(event);

//            System.out.println("Message: " + response + ", From IP: " + socket.getInetAddress().toString() + ", PORT: " + socket.getPort());
//            String[] chunks = response.trim().split("~");
//            if(chunks.length > 0 && chunks[0].equals("BROADCAST")) {
//                String toBroadcaster = "NODE_LIST";//TODO not count clients without UDP PORT
//
//                //TODO rewrite to stream
//                Client broadcaster = null;
//                for (Client client : clients) {
//                    if (client.getID() == Integer.parseInt(chunks[1])) {
//                        broadcaster = client;
//                    }
//                }
//                String toNodes = "MAKE_HOLE~" + broadcaster.getIP().toString().substring(1) + "-" + broadcaster.getUpdPort();
//                BufferedOutputStream bout = broadcaster.getOutput();
//                for (Client client : clients) {
//                    if (client == null || client.getID() == Integer.parseInt(chunks[1])) {
//                        continue;
//                    }
//                    BufferedOutputStream out = client.getOutput();
//                    try {
//                        out.write(toNodes.getBytes(StandardCharsets.UTF_8)); // the same socket for each client
//                        out.write('\n');
//                        out.flush();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    toBroadcaster = toBroadcaster + "~" + client.getIP().toString().substring(1) + "-" + client.getUpdPort();
//                }
//
//                try {
//                    bout.write(toBroadcaster.getBytes(StandardCharsets.UTF_8)); // the same socket for each client
//                    bout.write('\n');
//                    bout.flush();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//                break;
//            }
        }
    }
}

