package com.example.tableclearv2;

public class DataStructureGPS {
    private String mLat;
    private String mLon;

    public DataStructureGPS(){

    }

    public DataStructureGPS(String mLat, String mLon) {
        this.mLat = mLat;
        this.mLon = mLon;
    }

    public String getmLat() {
        return mLat;
    }

    public void setmLat(String mLat) {
        this.mLat = mLat;
    }

    public String getmLon() {
        return mLon;
    }

    public void setmLon(String mLon) {
        this.mLon = mLon;
    }
}
