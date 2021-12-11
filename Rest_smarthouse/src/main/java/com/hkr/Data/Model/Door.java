package com.hkr.Data.Model;

public class Door {
    int doorId;
    boolean doorStatus;

    public Door(int doorId, boolean doorStatus) {
        this.doorId = doorId;
        this.doorStatus = doorStatus;
    }

    public Door() {
    }

    public int getDoorId() {
        return doorId;
    }

    public void setDoorId(int doorId) {
        this.doorId = doorId;
    }

    public boolean isDoorStatus() {
        return doorStatus;
    }

    public void setDoorStatus(boolean doorStatus) {
        this.doorStatus = doorStatus;
    }
}
