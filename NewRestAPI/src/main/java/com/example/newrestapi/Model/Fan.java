package com.example.newrestapi.Model;

import java.io.Serializable;

public class  Fan implements Serializable {
   private int fanId ;
    private boolean fanStatus;

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

    @Override
    public String toString() {
        return "Fan{" +
                "fanId=" + fanId +
                ", fanStatus=" + fanStatus +
                '}';
    }
}
