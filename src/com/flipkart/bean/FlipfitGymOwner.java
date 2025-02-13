package com.flipkart.bean;

import java.util.List;

public class FlipfitGymOwner extends FlipfitUser {
    private String aadhar;
    private String PAN;
    private List<FlipfitGymCenter> gyms;

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

    public List<FlipfitGymCenter> getGyms() {
        return gyms;
    }

    public void setGyms(List<FlipfitGymCenter> gyms) {
        this.gyms = gyms;
    }
}
