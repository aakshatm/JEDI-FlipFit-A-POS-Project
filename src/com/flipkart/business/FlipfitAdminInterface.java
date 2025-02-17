package com.flipkart.business;

import com.flipkart.bean.FlipfitAdmin;
import com.flipkart.bean.FlipfitCustomer;
import com.flipkart.bean.FlipfitGymCenter;
import com.flipkart.bean.FlipfitGymOwner;

import java.util.List;

public interface FlipfitAdminInterface {

    /**
     * Logs in the admin using their email and password.
     *
     * @param email The admin's email.
     * @param password The admin's password.
     * @return true if login is successful; false otherwise.
     */
    boolean login(String email, String password);

    /**
     * Views the profile of the admin.
     *
     * @return The FlipfitAdmin object representing the admin's profile.
     */
    FlipfitAdmin viewProfile();

    /**
     * Edits the admin's profile with a new password.
     *
     * @param adminId The unique ID of the admin.
     * @param password The new password to be set.
     * @return true if the profile is updated successfully; false otherwise.
     */
    boolean editProfile(int adminId, String password);

    /**
     * Logs out the admin.
     *
     * @return true if logout is successful; false otherwise.
     */
    boolean logout();

    /**
     * Views the list of gym owners.
     *
     * @return A list of FlipfitGymOwner objects representing all gym owners.
     */
    List<FlipfitGymOwner> viewGymOwners();

    /**
     * Views the list of gyms.
     *
     * This method retrieves and displays all registered gyms.
     *
     * @return A list of FlipfitGymCenter objects representing all gyms.
     */
    List<FlipfitGymCenter> viewGyms();

    /**
     * Returns the list of users.
     *
     * This method retrieves and returns all registered customers.
     *
     * @return A list of FlipfitCustomer objects representing all customers.
     */
    List<FlipfitCustomer> viewCustomers();

    /**
     * Verifies the legitimacy of a gym.
     *
     * @param gymId The unique identifier of the gym to be verified.
     * @return true if the gym is verified successfully; false otherwise.
     */
    boolean verifyGym(int gymId);

    /**
     * Verifies the legitimacy of a gym owner.
     *
     * @param gymOwnerId The unique identifier of the gym owner to be verified.
     * @return true if the gym owner is verified successfully; false otherwise.
     */
    boolean verifyGymOwner(int gymOwnerId);

    /**
     * Retrieves a list of unverified gym owners.
     *
     * @return A list of FlipfitGymOwner objects representing unverified gym owners.
     */
    List<FlipfitGymOwner> getUnverifiedGymOwners();

    /**
     * Retrieves a list of unverified gyms.
     *
     * @return A list of FlipfitGymCenter objects representing unverified gyms.
     */
    List<FlipfitGymCenter> getUnverifiedGyms();
}
