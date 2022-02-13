package database;

import com.mongodb.*;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DBConnection {
    private static MongoClient mongoClient = null;

    private DBConnection(String dbUri) {
        MongoClientURI clientURI = new MongoClientURI(dbUri);
        try {
            mongoClient = new MongoClient(clientURI);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public DBConnection() {
    }


    public static void startDBConnection(String dbUri) {
        if (mongoClient == null) {
            MongoClientURI clientURI = new MongoClientURI(dbUri);
            try {
                mongoClient = new MongoClient(clientURI);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
    }

    public static void stopDBConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }


    public static DB getDatabase(String dbname) {
        if (mongoClient != null) {
            DB database = mongoClient.getDB(dbname);

            DBCollection collection = database.getCollection("warmup");
            DBObject simpleObject = new BasicDBObject()
                    .append("warmup-data", "initiates-db");
            collection.insert(simpleObject);
            return database;
        }
        return null;
    }

    public String getBlockchainJson() {
        // Need to be absolute for now
        String PATH = "/home/adam/notarial-blockchain/notarial-blockchain/src/main/java/database/Blockchain.json";
        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get(PATH)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}
