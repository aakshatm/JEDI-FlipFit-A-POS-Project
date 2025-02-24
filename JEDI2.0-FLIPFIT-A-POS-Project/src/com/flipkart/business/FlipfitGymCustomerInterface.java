package com.flipkart.business;

import com.flipkart.bean.Booking;
import com.flipkart.bean.FlipfitGymCenter;
import com.flipkart.bean.FlipfitCustomer;

import java.util.List;

/**
 * Interface for user-related operations.
 * Provides methods for booking and canceling slots, viewing gyms, and managing user details.
 *
 */
public interface FlipfitGymCustomerInterface {
    /**
     * Updates the password for the gym customer.
     *
     * @param email The email of the customer.
     * @param password The current password of the customer.
     * @param updatedPassword The new password to be set.
     * @return true if the password is updated successfully; false otherwise.
     */
    public boolean updateGymUserPassword(String email, String password, String updatedPassword);

    /**
     * Edits the profile of the customer.
     *
     * @param customerId The unique ID of the customer.
     * @param email The email address of the customer.
     * @param password The current password of the customer.
     * @param username The new username of the customer.
     * @param phoneNumber The new phone number of the customer.
     * @param address The new address of the customer.
     * @param location The new location of the customer.
     * @return true if the profile is updated successfully; false otherwise.
     */
    boolean editProfile(int customerId, String email, String password, String username, String phoneNumber, String address, String location);

    /**
     * Retrieves the profile of a customer.
     *
     * @param email The email address of the customer.
     * @param password The password of the customer.
     * @return A FlipfitCustomer object representing the customer's profile if valid credentials are provided; null otherwise.
     */
    FlipfitCustomer getProfile(String email, String password);
    /**
     * Cancels a slot booking based on the booking ID.
     *
     * @param bookingId the ID of the booking to be canceled
     * @return true if the cancellation was successful; false otherwise
     */
    boolean cancelSlot(int bookingId);

    /**
     * Retrieves all bookings made by the user.
     *
     * @param userId the ID of the user whose bookings are to be retrieved
     * @return a list of bookings made by the user
     */
    List<Booking> viewAllBookings(int userId);

    /**
     * Retrieves all gyms with available slots.
     *
     * @return a list of gyms with available slots
     */
    List<FlipfitGymCenter> viewAllGymsWithSlots();

    /**
     * Retrieves all gyms in a specified area.
     *
     * @param area the area in which to search for gyms
     * @return a list of gyms located in the specified area
     */
    List<FlipfitGymCenter> viewAllGymsByArea(String area);

    /**
     * Books a slot at a specified gym.
     *
     * @param gymId the ID of the gym where the slot is to be booked
     * @param startTime the start time of the slot
     * @param email the email of the user making the booking
     * @return true if the booking was successful; false otherwise
     */
    boolean bookSlot(int gymId, int startTime, String email);

    /**
     * Validates a user based on email and password.
     *
     * @param email the email of the user
     * @param password the password of the user
     * @return true if the credentials are valid; false otherwise
     */
    boolean validateUser(String email, String password);

    /**
     * Creates a new user.
     *
     * @param user the FlipfitCustomer object containing user details
     * @return true if the user was created successfully; false otherwise
     */
    boolean createUser(FlipfitCustomer user);

    /**
     * Updates the password for a user.
     *
     * @param email the email of the user
     * @param password the current password of the user
     * @param updatedPassword the new password to be set
     * @return true if the password was updated successfully; false otherwise
     */
    boolean updateUserPassword(String email, String password, String updatedPassword);

    /**
     * Updates user details.
     *
     * @param user the FlipfitCustomer object containing updated user details
     * @return true if the user details were updated successfully; false otherwise
     */
    boolean updateUserDetails(FlipfitCustomer user);

    /**
     * Retrieves the user ID based on the user's email.
     *
     * @param email the email of the user
     * @return the ID of the user associated with the email
     */
    int getUserIdByEmail(String email);

    /**
     * Retrieves the number of available seats at a specified gym and start time.
     *
     * @param gymId the ID of the gym
     * @param startTime the start time of the slot
     * @return the number of available seats
     */
    int getSeatCount(int gymId, int startTime);
}

