package com.flipkart.bean;

import java.util.List;

public class GymCustomer extends User {
    private int age;
    private List<Booking> bookings;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
