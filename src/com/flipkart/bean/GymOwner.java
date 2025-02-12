package com.flipkart.bean;

import java.util.List;

public class GymOwner {
    private String aadhar;
    private String PAN;
    private List<GymCenter> gyms;

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getPAN() {
        return PAN;
    }

    public void setPAN(String PAN) {
        this.PAN = PAN;
    }

    public List<GymCenter> getGyms() {
        return gyms;
    }

    public void setGyms(List<GymCenter> gyms) {
        this.gyms = gyms;
    }
}
