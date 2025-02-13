package com.flipkart.business;

import com.flipkart.bean.FlipfitCustomer;
import com.flipkart.bean.Payment;

public interface FlipfitGymCustomerInterface {

    // View available slots at a given center on a particular date
    public void viewAvailableSlots(int centerId, String date);

    // Find gym centers by location (city)
    public void findGymByLocation(String location);

    // Make payment for a confirmed booking
    public void makePayment(int bookingId, Payment payment);

    // Register for a waiting list if the slot is full
    public void registerForWaitingList(int centerId, int slotId, FlipfitCustomer user);

    // Book a gym slot for a user
    public void bookGymSlot(FlipfitCustomer user, int slotId, int gymId);

    // Cancel a booking by bookingId
    public void cancelBooking(FlipfitCustomer user, int bookingId);
}
