package com.example.tableclearv2;

public class DataStructureReservation {
    private String mResName;
    private String mResAddress;
    private String mCusName;
    private String mCusPhone;
    private String mTime;
    private String mDate;
    private String mTable;

    public DataStructureReservation(String mResName, String mResAddress, String mCusName, String mCusPhone, String mTime, String mDate, String mTable) {
        this.mResName = mResName;
        this.mResAddress = mResAddress;
        this.mCusName = mCusName;
        this.mCusPhone = mCusPhone;
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

    public String getmCusName() {
        return mCusName;
    }

    public void setmCusName(String mCusName) {
        this.mCusName = mCusName;
    }

    public String getmCusPhone() {
        return mCusPhone;
    }

    public void setmCusPhone(String mCusPhone) {
        this.mCusPhone = mCusPhone;
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
