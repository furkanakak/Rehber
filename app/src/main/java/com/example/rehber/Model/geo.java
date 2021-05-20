package com.example.rehber.Model;

public class geo {


    private String lat;
    private geo lng;

    public geo() {
    }

    public geo(String lat, geo lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public geo getLng() {
        return lng;
    }

    public void setLng(geo lng) {
        this.lng = lng;
    }
}
