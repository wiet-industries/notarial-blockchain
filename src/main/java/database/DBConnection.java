package database;

import com.mongodb.*;

import java.net.UnknownHostException;

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
}
