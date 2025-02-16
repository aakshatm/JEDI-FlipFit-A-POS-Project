package com.flipkart.business;

import com.flipkart.bean.FlipfitCustomer;
import com.flipkart.bean.Payment;
import com.flipkart.bean.FlipfitGymCenter;
import com.flipkart.bean.FlipfitGymOwner;

import java.util.List;

public interface FlipfitGymOwnerInterface {
    /**
     * Adds a new gym.
     *
     * @param gym the FlipfitGymCenter object to be added
     * @return true if the gym was added successfully; false otherwise
     */
    boolean addGym(FlipfitGymCenter gym);

    /**
     * Views the list of gyms owned by a specific gym owner.
     *
     * @param gymOwnerId the unique identifier of the gym owner whose gyms are to be viewed
     * @return a list of FlipfitGymCenter objects owned by the specified gym owner
     */
    List<FlipfitGymCenter> viewMyGyms(int gymOwnerId);

    /**
     * Validates a gym owner's credentials.
     *
     * @param email the email of the gym owner
     * @param password the current password of the gym owner
     * @return true if the email and password are valid; false otherwise
     */
    boolean validateGymOwner(String email, String password);

    /**
     * Creates a new gym owner.
     *
     * @param gymOwner the FlipfitGymOwner object containing details of the new gym owner
     * @return true if the gym owner was created successfully; false otherwise
     */
    boolean createGymOwner(FlipfitGymOwner gymOwner);

    /**
     * Updates the password for a gym owner.
     *
     * @param email the email of the gym owner
     * @param password the current password of the gym owner
     * @param updatedPassword the new password to be set
     * @return true if the password was updated successfully; false otherwise
     */
    boolean updateGymOwnerPassword(String email, String password, String updatedPassword);

    /**
     * Updates the details of an existing gym owner.
     *
     * @param gymOwner the FlipfitGymOwner object containing updated details of the gym owner
     * @return true if the gym owner details were updated successfully; false otherwise
     */
    boolean updateGymOwner(FlipfitGymOwner gymOwner);

    /**
     * Retrieves the unique identifier of a gym owner based on their email.
     *
     * @param email the email of the gym owner
     * @return the unique identifier of the gym owner
     */
    int getGymOwnerIdByEmail(String email);

    /**
     * Updates the seat count for a specific gym slot.
     *
     * @param gymId the unique identifier of the gym
     * @param startTime the start time of the slot
     * @param seatCount the new seat count to be set for the slot
     * @return true if the seat count was updated successfully; false otherwise
     */
    boolean updateSeatCount(int gymId, int startTime, int seatCount);

    /**
     * Updates user details.
     *
     * @param gym the FlipfitGymCenter object containing updated gym details
     * @return true if the gym details were updated successfully; false otherwise
     */
    boolean updateGymDetails(FlipfitGymCenter gym);

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
