package com.example.hixam.trainwakeup.clases;

/**
 * Created by hixam on 26/02/17.
 */
public class Station {

    private long id;
    private String station;
    private String lat;
    private String longt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongt() {
        return longt;
    }

    public void setLongt(String longt) {
        this.longt = longt;
    }


    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return station;
    }
}