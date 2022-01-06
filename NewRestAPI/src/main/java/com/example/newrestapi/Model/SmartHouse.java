package com.example.newrestapi.Model;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Arrays;

public class SmartHouse implements Serializable {
    private Light[] lights;
    private Door[] doors;
    private Window[] windows;
    private int humidity;
    private int temperature;


    // empty Constructor
    public SmartHouse() {

    }

    public SmartHouse(Light[] lights, Door[] doors, Window[] windows, int humidity, int temperature) {
        this.lights = lights;
        this.doors = doors;
        this.windows = windows;
        this.humidity = humidity;
        this.temperature = temperature;
    }

    public Light[] getLights() {
        return lights;
    }

    public Light getLight(int index) {
        return lights[index];
    }

    public void setLight(Light light) {
        for (int i = 0; i < lights.length; i++) {
            if (lights[i] == null)
                lights[i] = light;
        }

    }

    public Door getDoor(int index) {
        return doors[index];
    }

    public void setDoor(Door door) {
        for (int i = 0; i < doors.length; i++) {
            if (doors[i] == null)
                doors[i] = door;
        }

    }

    public Window getWindow(int index) {
        return windows[index];
    }

    public void setWindow(Window window) {
        for (int i = 0; i <= windows.length; i++) {
            if (windows[i] == null)
                windows[i] = window;
        }

    }


    public void setLights(Light[] lights) {
        this.lights = lights;
    }

    public Door[] getDoors() {
        return doors;
    }

    public void setDoors(Door[] door) {
        this.doors = door;
    }

    public Window[] getWindows() {
        return windows;
    }

    public void setWindows(Window[] window) {
        this.windows = window;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "SmartHouse{" +
                "lights=" + Arrays.toString(lights) +
                ", doors=" + Arrays.toString(doors) +
                ", windows=" + Arrays.toString(windows) +
                ", humidity=" + humidity +
                ", temperature=" + temperature +
                '}';
    }
}