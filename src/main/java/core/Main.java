package core;

import core.utils.Config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static core.utils.EnvConfig.getAuthorizedUsersAddressesFilePath;

public class Main {
    public static void main(String[] args) throws IOException {
        List<InetAddress> authorizedUsers = getAuthorizedUsersList();
        Server serverInstance = new Server(Config.TCP_PORT, Config.UDP_PORT, authorizedUsers);
        serverInstance.startServer();
    }

    private static List<InetAddress> getAuthorizedUsersList() {
        String path = getAuthorizedUsersAddressesFilePath();
        List<InetAddress> result = new ArrayList<>();

        try {
            File file = new File(path);    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
            StringBuffer sb= new StringBuffer();    //constructs a string buffer with no characters
            String line;
            while((line=br.readLine())!=null) {
                InetAddress address = InetAddress.getByName(line);
                result.add(address);
            }
            fr.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
