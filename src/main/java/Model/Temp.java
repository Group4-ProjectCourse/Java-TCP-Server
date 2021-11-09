package Model;

public class Temp {
    float cTemp;
    float fTemp;

    public Temp(float cTemp, float fTemp) {
        this.cTemp = cTemp;
        this.fTemp = fTemp;
    }

    public float getcTemp() {
        return cTemp;
    }

    public void setcTemp(float cTemp) {
        this.cTemp = cTemp;
    }

    public float getfTemp() {
        return fTemp;
    }

    public void setfTemp(float fTemp) {
        this.fTemp = fTemp;
    }

    public Temp() {
    }

}
