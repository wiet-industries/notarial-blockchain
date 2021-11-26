package main.core;

import java.util.List;

public interface ServerStrategy {
    Client processAuthor(String ID);
    boolean respondToAuthor(int ID);
    boolean respondToOthers(int[] IDs);
    boolean updateClients(List<Client> clientList);
}
