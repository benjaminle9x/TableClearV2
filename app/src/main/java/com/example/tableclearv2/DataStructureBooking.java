package com.example.tableclearv2;

public class DataStructureBooking {
    private String mResName;
    private String mResAddress;
    private String mTime;
    private String mDate;
    private String mTable;

    public DataStructureBooking(String mResName, String mResAddress, String mTime, String mDate, String mTable) {
        this.mResName = mResName;
        this.mResAddress = mResAddress;
        this.mTime = mTime;
        this.mDate = mDate;
        this.mTable = mTable;
    }

    public String getmResName() {
        return mResName;
    }

    public void setmResName(String mResName) {
        this.mResName = mResName;
    }

    public String getmResAddress() {
        return mResAddress;
    }

    public void setmResAddress(String mResAddress) {
        this.mResAddress = mResAddress;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmTable() {
        return mTable;
    }

    public void setmTable(String mTable) {
        this.mTable = mTable;
    }
}
