package com.flipkart.business;

import com.flipkart.bean.FlipfitUser;

public class FlipfitGymCustomerService implements FlipfitGymCustomerInterface {
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

    public void bookGymSlot(FlipfitUser user, int slotId) {
        System.out.println(user.getName() + " booked slot ID: " + slotId);
    }

    public void cancelBooking(FlipfitUser user, int bookingId) {
        System.out.println(user.getName() + " canceled booking ID: " + bookingId);
    }

    public void cancelBooking(FlipfitUser user, String date) {
        System.out.println(user.getName() + " is viewing their workout plan for " + date);
    }
}
