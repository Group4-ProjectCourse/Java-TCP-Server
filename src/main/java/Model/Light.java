package Model;

public class Light {
    int lightId ;
    boolean lightStatus;

    public Light(int lightId, boolean lightStatus) {
        this.lightId = lightId;
        this.lightStatus = lightStatus;
    }

    public int getLightId() {
        return lightId;
    }

    public void setLightId(int lightId) {
        this.lightId = lightId;
    }

    public boolean isLightStatus() {
        return lightStatus;
    }

    public void setLightStatus(boolean lightStatus) {
        this.lightStatus = lightStatus;
    }
}
