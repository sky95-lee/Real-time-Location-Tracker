package com.app.usertrackers.dto;

import com.google.gson.annotations.SerializedName;

public class IPResponse {

    @SerializedName("ip")
    public String ipAddress;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
