package com.example.bf.kf.DAO.bean;

import java.io.Serializable;

/**
 * 设备对象
 */
public class DeviceBean implements Serializable {
    private String deviceId;//设备id

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
