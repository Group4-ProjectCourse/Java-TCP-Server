package app.database;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class MagneticCard {
    @Id
    private ObjectId id;
    private String uuid;
    private String password;

    public MagneticCard() {

    }

    public MagneticCard(String uuid, String password) {
        super();
        this.uuid = uuid;
        this.password = password;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDefaultPicture() {
        return "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.hkr.se%2FStatic%2Fgfx%2Fog_image.png";
    }
}