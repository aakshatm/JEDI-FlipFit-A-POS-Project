package com.flipkart.bean;

import java.util.List;

public class Slot {
    private int startTime;
    private int endTime;
    private int capacity;
    private List<Booking> bookings;
    private List<Booking> waitlisted;

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<Booking> getWaitlisted() {
        return waitlisted;
    }

    public void setWaitlisted(List<Booking> waitlisted) {
        this.waitlisted = waitlisted;
    }
}
