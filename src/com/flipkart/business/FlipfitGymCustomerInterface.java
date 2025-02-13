package com.flipkart.business;
import com.flipkart.bean.FlipfitUser;

public interface FlipfitGymCustomerInterface {

    public void viewAvailableSlots(int centerId, String date);
    public void findGymByLocation(String location);
    public void makePayment();
    public void registerForWaitingList(int centerId, int slot_id);
    public void bookGymSlot(FlipfitUser user, int slotId);
    public void cancelBooking(FlipfitUser user, int bookingId);
    public void cancelBooking(FlipfitUser user, String date);
}
