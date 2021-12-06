package core;

import com.google.gson.Gson;
import core.models.PayloadMessage;
import core.utils.Config;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Server serverInstance = new Server(Config.TCP_PORT, Config.UDP_PORT);
        serverInstance.startServer();
    }
}
