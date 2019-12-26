package com.example.tableclearv2;

public class DataStructureCustomer {
    private String fullname;
    private String phone;
    private String address;

    public DataStructureCustomer(){

    }

    public DataStructureCustomer(String fullname, String phone, String address) {
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
