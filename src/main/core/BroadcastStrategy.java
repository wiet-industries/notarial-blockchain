package main.core;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class BroadcastStrategy implements ServerStrategy {
    @Override
    public Client processAuthor(SocketPayload socketPayload, List<Client> clientList) {
        String payloadMessage = socketPayload.getPayloadMessage().getMessageData();//.split(PayloadMessage.SEPARATOR); // XDDD
        JSONObject jo = new JSONObject(payloadMessage);
        int ID = Integer.parseInt((String) jo.opt("id"));

        System.out.println("Broadcast from: " + ID);
        for (Client client : clientList) {
            if (client.getID() == ID) {
                return client;
            }
        }
        return null;
    }

    @Override
    public boolean respondToAuthor(Client client, List<Client> clientList) {
        //String message = "NODE_LIST~" + client.getID() + "~"; //TODO not count clients without UDP PORT
        JSONObject jo = new JSONObject();
        jo.put("id", client.getID());
        jo.put("type", "NODE_LIST");
        ArrayList<String> addresses = new ArrayList<String>();

        for (Client c : clientList) {
            if (c != null && client.getID() != c.getID()) {
//                if (message.charAt(message.length() - 1) != '~') {
//                    message += "-";
//                }
                addresses.add(c.getIP().toString().substring(1) + ":" + c.getUpdPort());
                //message += c.getIP().toString().substring(1) + ":" + c.getUpdPort();
            }
        }
        jo.put("content", addresses);
        BufferedOutputStream output = client.getOutput();
        try {
            output.write(jo.toString().getBytes(StandardCharsets.UTF_8));
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
                JSONObject jo = new JSONObject();
                jo.put("id", c.getID());
                jo.put("type", "OPEN_REQUEST");
                jo.put("content", c.getIP().toString().substring(1) + ":" + c.getUpdPort());
                BufferedOutputStream output = c.getOutput();
                  try {
                    output.write(jo.toString().getBytes(StandardCharsets.UTF_8));
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
