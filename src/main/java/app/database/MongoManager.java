package app.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoManager {
    private static final MongoClient mongoClient;

    static {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://java-rest-server:F5UqUEjPdKUMMJi9@mysport.7yryy.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        mongoClient = MongoClients.create(settings);
    }

    public static MongoDatabase getDatabase() {
        return mongoClient.getDatabase("smart-house");
    }
}