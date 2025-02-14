package com.flipkart.bean;

import java.util.ArrayList;
import java.util.List;

public class FlipfitCustomer extends FlipfitUser {
    private int age;
    private List<Booking> bookings;

    public FlipfitCustomer(){
        bookings = new ArrayList<>();
    }

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
