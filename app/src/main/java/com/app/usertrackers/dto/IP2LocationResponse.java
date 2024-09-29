package com.app.usertrackers.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IP2LocationResponse implements Serializable {

    @SerializedName("ip")
    private String ipAddress;

    @SerializedName("country_code")
    private String countryCode;

    @SerializedName("country_name")
    private String countryName;

    @SerializedName("region_name")
    private String RegionName;

    @SerializedName("city_name")
    private String cityName;

    @SerializedName("latitude")
    private Double latitude;

    @SerializedName("longitude")
    private Double longitude;

    @SerializedName("zip_code")
    private String zipCode;

    @SerializedName("time_zone")
    private String timeZone;

    @SerializedName("asn")
    private String asn;

    @SerializedName("as")
    private String as;

    @SerializedName("is_proxy")
    private Boolean isProxy;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getRegionName() {
        return RegionName;
    }

    public void setRegionName(String regionName) {
        RegionName = regionName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getAsn() {
        return asn;
    }

    public void setAsn(String asn) {
        this.asn = asn;
    }

    public String getAs() {
        return as;
    }

    public void setAs(String as) {
        this.as = as;
    }

    public Boolean getIsProxy() {
        return isProxy;
    }

    public void setIsProxy(Boolean isProxy) {
        this.isProxy = isProxy;
    }
}
