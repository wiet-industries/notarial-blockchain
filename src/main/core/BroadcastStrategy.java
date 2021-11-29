package main.core;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class BroadcastStrategy implements ServerStrategy {
    @Override
    public Client processAuthor(SocketPayload socketPayload, List<Client> clientList) {
        String[] payloadMessage = socketPayload.getPayloadMessage().getMessageData().split(PayloadMessage.SEPARATOR);
        //String[] chunks = payloadMessage.getMessageData().split(PayloadMessage.SEPARATOR);
        System.out.println("Broadcast " + payloadMessage[0]);
        for (Client client : clientList) {
            if (client.getID() == Integer.parseInt(payloadMessage[0])) {
                return client;
            }
        }
        return null;
    }

    @Override
    public boolean respondToAuthor(Client client, List<Client> clientList) {
        System.out.println("Sending to broadcast requester");
        String toBroadcaster = "NODE_LIST~" + client.getID() + "~";//TODO not count clients without UDP PORT

        //TODO rewrite to stream


        for (Client c : clientList) {
            if (c != null && client.getID() != c.getID()) {
                if (toBroadcaster.charAt(toBroadcaster.length() - 1) != '~') {
                    toBroadcaster += "-";
                }
                toBroadcaster += c.getIP().toString().substring(1) + ":" + c.getUpdPort();
            }


        }
        BufferedOutputStream output = client.getOutput();
        try {
            output.write(toBroadcaster.getBytes(StandardCharsets.UTF_8)); // the same socket for each client
            output.write('\n');
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean respondToOthers(Client client, List<Client> clientList) {


        for (Client c : clientList) {
            if (c != null && client.getID() != c.getID()) {
                BufferedOutputStream output = c.getOutput();
                String message = "MAKE_HOLE~" + c.getID() + "~" + c.getIP().toString().substring(1) + ":" + c.getUpdPort();
                try {
                    output.write(message.getBytes(StandardCharsets.UTF_8)); // the same socket for each client
                    output.write('\n');
                    output.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return true;
    }

    @Override
    public boolean updateClients(Client client, List<Client> clientList) {
        return false;
    }
}
