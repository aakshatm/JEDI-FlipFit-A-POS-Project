package com.flipkart.business;

import com.flipkart.bean.User;

import java.sql.SQLOutput;

public class GymCustomerService extends UserService {
    public void viewAvailableSlots(int centerId, String date) {
        System.out.println("Viewing available slots for Center ID: " + centerId + " on " + date);
    }

    public void findGymByLocation(String location){

    }

    public void makePayment(){

    }

    public void registerForWaitingList(int centerId, int slot_id){
        System.out.println("Added to waiting list of "+ centerId + " for the slot " + slot_id);
    }

    public void bookGymSlot(User user, int slotId) {
        System.out.println(user.getName() + " booked slot ID: " + slotId);
    }

    public void cancelBooking(User user, int bookingId) {
        System.out.println(user.getName() + " canceled booking ID: " + bookingId);
    }

    public void cancelBooking(User user, String date) {
        System.out.println(user.getName() + " is viewing their workout plan for " + date);
    }
}
