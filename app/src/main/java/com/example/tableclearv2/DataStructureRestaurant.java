package com.example.tableclearv2;

public class DataStructureRestaurant {
    private String resName;
    private String resAddress;
    private String resRating;

    public DataStructureRestaurant() {

    }

    public DataStructureRestaurant(String resName, String resAddress, String resRating) {
        this.resName = resName;
        this.resAddress = resAddress;
        this.resRating = resRating;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResAddress() {
        return resAddress;
    }

    public void setResAddress(String resAddress) {
        this.resAddress = resAddress;
    }

    public String getResRating() {
        return resRating;
    }

    public void setResRating(String resRating) {
        this.resRating = resRating;
    }
}
