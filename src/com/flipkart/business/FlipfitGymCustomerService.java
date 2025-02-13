package com.flipkart.business;

import com.flipkart.bean.*;

import java.util.*;

public class FlipfitGymCustomerService implements FlipfitGymCustomerInterface {

    private Map<Integer, FlipfitCustomer> customers = new HashMap<>();
    private Map<Integer, FlipfitGymCenter> gyms = new HashMap<>();
    private Map<Integer, Booking> bookings = new HashMap<>();

    // View Available Slots
    public void viewAvailableSlots(int centerId, String date) {
        FlipfitGymCenter gym = gyms.get(centerId);
        if (gym != null) {
            System.out.println("Available Slots for " + gym.getGymName() + " on " + date + ":");
            for (Slot slot : gym.getSlots()) {
                System.out.println("Slot: " + slot.getStartTime() + " - " + slot.getEndTime() + " Capacity: " + slot.getCapacity());
            }
        } else {
            System.out.println("Gym Center not found.");
        }
    }

    // Find Gym by Location
    public void findGymByLocation(String location) {
        for (FlipfitGymCenter gym : gyms.values()) {
            if (gym.getGymCity().equalsIgnoreCase(location)) {
                System.out.println("Found gym: " + gym.getGymName() + " in " + location);
            }
        }
    }

    // Make Payment
    public void makePayment(int bookingId, Payment payment) {
        Booking booking = bookings.get(bookingId);
        if (booking != null && booking.isConfirmed()) {
            System.out.println("Payment of " + payment.getAmount() + " using " + payment.getPaymentMode() + " made successfully for booking ID: " + bookingId);
        } else {
            System.out.println("Booking not confirmed. Unable to make payment.");
        }
    }

    // Register for Waiting List
    public void registerForWaitingList(int centerId, int slotId, FlipfitCustomer customer) {
        FlipfitGymCenter gym = gyms.get(centerId);
        if (gym != null) {
            Slot slot = getSlotById(gym, slotId);
            if (slot != null) {
                if (slot.getBookings().size() >= slot.getCapacity()) {
                    slot.getWaitlisted().add(new Booking()); // Add customer to the waitlist
                    System.out.println(customer.getName() + " added to the waitlist for Slot ID: " + slotId);
                } else {
                    System.out.println("Slot not full. Booking available.");
                }
            }
        }
    }

    // Book Gym Slot
    public void bookGymSlot(FlipfitCustomer user, int slotId, int gymId) {
        FlipfitGymCenter gym = gyms.get(gymId);
        if (gym != null) {
            Slot slot = getSlotById(gym, slotId);
            if (slot != null && slot.getBookings().size() < slot.getCapacity()) {
                Booking newBooking = new Booking();
                newBooking.setBookingId(bookings.size() + 1);
                newBooking.setGymId(gymId);
                newBooking.setSlot(slotId);
                newBooking.setConfirmed(true);

                slot.getBookings().add(newBooking);
                bookings.put(newBooking.getBookingId(), newBooking);
                System.out.println(user.getName() + " booked Slot ID: " + slotId + " at Gym: " + gym.getGymName());
            } else {
                System.out.println("Slot is full or not available.");
            }
        }
    }

    // Cancel Booking
    public void cancelBooking(FlipfitCustomer user, int bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking != null) {
            FlipfitGymCenter gym = gyms.get(booking.getGymId());
            if (gym != null) {
                Slot slot = getSlotById(gym, booking.getSlot());
                if (slot != null) {
                    slot.getBookings().remove(booking);
                    bookings.remove(bookingId);
                    System.out.println(user.getName() + " canceled booking ID: " + bookingId);
                }
            }
        }
    }

    // Helper method to get slot by ID
    private Slot getSlotById(FlipfitGymCenter gym, int slotId) {
        for (Slot slot : gym.getSlots()) {
            if (slot.getStartTime() == slotId) {
                return slot;
            }
        }
        return null;
    }
}
