package com.hkr.Data.Model;

public class SmartHouse {
    private Light light ;
    private Door door  ;
    private Window window ;
    private Temp cTemp ;
    private Temp fTemp ;

    public SmartHouse() {
    }

    public Light getLight() {
        return light;
    }

    public void setLight(Light light) {
        this.light = light;
    }

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public Temp getcTemp() {
        return cTemp;
    }

    public void setcTemp(Temp cTemp) {
        this.cTemp = cTemp;
    }

    public Temp getfTemp() {
        return fTemp;
    }

    public void setfTemp(Temp fTemp) {
        this.fTemp = fTemp;
    }
}
