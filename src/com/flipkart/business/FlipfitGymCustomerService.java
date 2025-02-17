package com.flipkart.business;

import com.flipkart.bean.Booking;
import com.flipkart.bean.FlipfitGymCenter;
import com.flipkart.bean.FlipfitCustomer;
import com.flipkart.DAO.FlipFitCustomerDAOImplementation;
import com.flipkart.DAO.FlipFitUpdatePasswordDAOImplementation;

import java.util.*;


/**
 * Provides operations related to user services including user creation, updating user details,
 * validating users, and managing bookings.
 * Implements UserService interface.
 */
public class FlipfitGymCustomerService implements FlipfitGymCustomerInterface {

    /**
     * Data access object for customer-related operations.
     */
    FlipFitCustomerDAOImplementation flipFitCustomerDAOImplementation = new FlipFitCustomerDAOImplementation();

    /**
     * Data access object for updating user passwords.
     */
    FlipFitUpdatePasswordDAOImplementation flipFitUpdatePasswordDAOImplementation = new FlipFitUpdatePasswordDAOImplementation();


    @Override
    /**
     * Edits the profile of the customer by updating the provided details.
     *
     * @param customerId The unique ID of the customer whose profile is to be updated.
     * @param email The email address of the customer.
     * @param password The current password of the customer.
     * @param username The new username to be set for the customer.
     * @param phoneNumber The new phone number of the customer.
     * @param address The new address of the customer.
     * @param location The new location of the customer.
     * @return true if the profile is updated successfully; false otherwise.
     */
    public boolean editProfile(int customerId, String email, String password, String username, String phoneNumber, String address, String location) {
        return flipFitCustomerDAOImplementation.editProfile(customerId, email, password, username, phoneNumber, address, location);
    }

    @Override
    /**
     * Retrieves the profile of a customer using the provided credentials.
     *
     * @param email The email address of the customer.
     * @param password The password of the customer.
     * @return A FlipfitCustomer object representing the customer's profile if valid credentials are provided; null otherwise.
     */
    public FlipfitCustomer getProfile(String email, String password) {
        return flipFitCustomerDAOImplementation.getProfile(email, password);
    }

    /**
     * Creates a new user in the system.
     *
     * @param user the user object containing the user's details
     * @return true if user creation is successful, false otherwise
     */

    public boolean createUser(FlipfitCustomer user) {
        return flipFitCustomerDAOImplementation.createUser(user);
    }

    /**
     * Updates the password of a user
     *
     * @param email           the email of the user
     * @param password        the current password of the user
     * @param updatedPassword the new password to set
     * @return false, as the implementation is not provided
     */
    @Override
    public boolean updateUserPassword(String email, String password, String updatedPassword) {
        return flipFitUpdatePasswordDAOImplementation.updateGymUserPassword(email, password, updatedPassword);
    }

    /**
     * Validates a user's credentials.
     *
     * @param email    the email of the user
     * @param password the password of the user
     * @return true if the user is valid, false otherwise
     */
    public boolean validateUser(String email, String password) {
        return flipFitCustomerDAOImplementation.validateUser(email, password);
    }

    /**
     * Updates the password for a gym user.
     *
     * @param email           the email of the gym user
     * @param password        the current password of the gym user
     * @param updatedPassword the new password to set
     * @return true if password update is successful, false otherwise
     */
    @Override
    public boolean updateGymUserPassword(String email, String password, String updatedPassword) {
        return flipFitUpdatePasswordDAOImplementation.updateGymUserPassword(email, password, updatedPassword);
    }

    /**
     * Updates the details of a user.
     *
     * @param user the user object containing updated user details
     * @return true if user details update is successful, false otherwise
     */
    public boolean updateUserDetails(FlipfitCustomer user) {
        return flipFitCustomerDAOImplementation.updateUserDetails(user);
    }

    /**
     * Retrieves the user ID associated with a given email.
     *
     * @param email the email of the user
     * @return the user ID associated with the given email
     */
    public int getUserIdByEmail(String email) {
        return flipFitCustomerDAOImplementation.getUserIdByEmail(email);
    }

    /**
     * Books a slot at a gym for a user.
     *
     * @param gymId     the ID of the gym
     * @param startTime the start time of the slot
     * @param email     the email of the user
     * @return true if the booking is successful, false otherwise
     */
    public boolean bookSlot(int gymId, int startTime, String email) {
        return flipFitCustomerDAOImplementation.bookSlot(gymId, startTime, email);
    }

    /**
     * Cancels a booking.
     *
     * @param bookingId the ID of the booking to cancel
     * @return true if the cancellation is successful, false otherwise
     */
    public boolean cancelSlot(int bookingId) {
        return flipFitCustomerDAOImplementation.cancelBooking(bookingId);
    }

    /**
     * Retrieves all bookings made by a user.
     *
     * @param userId the ID of the user
     * @return a list of bookings associated with the user
     */
    public List<Booking> viewAllBookings(int userId) {
        return flipFitCustomerDAOImplementation.getAllBookingsByUserID(userId);
    }

    /**
     * Retrieves all gyms that have available slots.
     *
     * @return a list of gyms with available slots
     */
    public List<FlipfitGymCenter> viewAllGymsWithSlots() {
        return flipFitCustomerDAOImplementation.viewAllGymsWithSlots();
    }

    /**
     * Retrieves all gyms in a specified area.
     *
     * @param area the area to search for gyms
     * @return a list of gyms located in the specified area
     */
    public List<FlipfitGymCenter> viewAllGymsByArea(String area) {
        return flipFitCustomerDAOImplementation.viewAllGymsByArea(area);
    }

    /**
     * Retrieves the seat count available at a gym for a given time slot.
     *
     * @param gymId     the ID of the gym
     * @param startTime the start time of the slot
     * @return the number of available seats at the gym for the given time
     */
    public int getSeatCount(int gymId, int startTime) {
        return flipFitCustomerDAOImplementation.getSeatCount(gymId, startTime);
    }
}
