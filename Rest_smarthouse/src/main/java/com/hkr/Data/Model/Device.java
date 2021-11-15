package com.hkr.Data.Model;

import javax.inject.Named;
import javax.ws.rs.Path;
import java.io.Serializable;


public class Device implements Serializable {
    private String LightSwitch;
    private String DoorSwitch;
    private int Humidity;
    private int Temperature;
    private String WindowSwitch;


    // empty COnstructor
    public Device() {
    }

    public Device(String lightSwitch, String doorSwitch, int humidity, int temperature, String windowSwitch) {
        LightSwitch = lightSwitch;
        DoorSwitch = doorSwitch;
        Humidity = humidity;
        Temperature = temperature;
        WindowSwitch = windowSwitch;
    }

    public int getHumidity() {
        return Humidity;
    }

    public void setHumidity(int humidity) {
        Humidity = humidity;
    }

    public int getTemperature() {
        return Temperature;
    }

    public void setTemperature(int temperature) {
        Temperature = temperature;
    }

    public String getWindowSwitch() {
        return WindowSwitch;
    }

    public void setWindowSwitch(String windowSwitch) {
        WindowSwitch = windowSwitch;
    }

    public String getLightSwitch() {
        return LightSwitch;
    }

    public void setLightSwitch(String lightSwitch) {
        LightSwitch = lightSwitch;
    }

    public String getDoorSwitch() {
        return DoorSwitch;
    }

    public void setDoorSwitch(String doorSwitch) {
        DoorSwitch = doorSwitch;
    }

    @Override
    public String toString() {
        return "DeviceTest{" +
                "LightSwitch='" + LightSwitch + '\'' +
                ", DoorSwitch='" + DoorSwitch + '\'' +
                ", Humidity=" + Humidity +
                ", Temperature=" + Temperature +
                ", WindowSwitch='" + WindowSwitch + '\'' +
                '}';
    }
}