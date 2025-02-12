package com.flipkart.bean;

import java.util.List;

public class GymCenter {
    private int gymId;
    private String gymName;
    private String gymCity;
    private float gymPrice;
    private List<Slot> slots;

    public int getGymId() {
        return gymId;
    }

    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    public String getGymName() {
        return gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public String getGymCity() {
        return gymCity;
    }

    public void setGymCity(String gymCity) {
        this.gymCity = gymCity;
    }

    public float getGymPrice() {
        return gymPrice;
    }

    public void setGymPrice(float gymPrice) {
        this.gymPrice = gymPrice;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }
}
