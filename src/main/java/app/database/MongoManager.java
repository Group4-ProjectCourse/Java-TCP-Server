package app.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoManager {
    private static final MongoClient mongoClient;

    static {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        ConnectionString connectionString = new ConnectionString("mongodb+srv://java-rest-server:F5UqUEjPdKUMMJi9@mysport.7yryy.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(pojoCodecRegistry)
                .build();
        mongoClient = MongoClients.create(settings);
    }

    public boolean verifyUUID(MagneticCard card) {
        MongoCollection<MagneticCard> collection = getDatabase().getCollection("magnetic-cards", MagneticCard.class);

        MagneticCard result = collection.find(eq("uuid", card.getUUID())).first();

        return result != null;
    }

    public boolean verifyPassword(MagneticCard card) {
        MongoCollection<MagneticCard> collection = getDatabase().getCollection("magnetic-cards", MagneticCard.class);

        MagneticCard result = collection.find(and(
                eq("uuid", card.getUUID()),
                eq("password", card.getPassword())
        )).first();

        return result != null;
    }

    public static MongoDatabase getDatabase() {
        return mongoClient.getDatabase("smart-house");
    }

    static class QueryResult {
        public boolean uuid, password;

        public QueryResult(boolean uuid, boolean password) {
            this.uuid = uuid;
            this.password = password;
        }
    }
}