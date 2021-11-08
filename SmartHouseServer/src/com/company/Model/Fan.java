package com.company.Model;

public class Fan {
    int fanId ;
    boolean fanStatus;

    public Fan() {
    }

    public int getFanId() {
        return fanId;
    }

    public void setFanId(int fanId) {
        this.fanId = fanId;
    }

    public boolean isFanStatus() {
        return fanStatus;
    }

    public void setFanStatus(boolean fanStatus) {
        this.fanStatus = fanStatus;
    }

    public Fan(int fanId, boolean fanStatus) {
        this.fanId = fanId;
        this.fanStatus = fanStatus;
    }
}
