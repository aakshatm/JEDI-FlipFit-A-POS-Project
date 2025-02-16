package com.flipkart.business;

import com.flipkart.bean.FlipfitAdmin;
import com.flipkart.bean.FlipfitCustomer;
import com.flipkart.bean.FlipfitGymCenter;
import com.flipkart.bean.FlipfitGymOwner;

import java.util.List;

public interface FlipfitAdminInterface {
    boolean login(String email, String password);
    FlipfitAdmin viewProfile();
    boolean editProfile(int adminId, String password);

    boolean logout();

    List<FlipfitGymOwner> viewGymOwners();

    /**
     * Views the list of gyms.
     *
     * This method retrieves and displays all registered gyms.
     *
     */
    List<FlipfitGymCenter> viewGyms();

    /**
     * Returns the list of users.
     *
     * This method retrieves and returns all registered users.
     *
     */
    List<FlipfitCustomer> viewCustomers();

    /**
     * Verifies the legitimacy of a gym.
     *
     * @param gymId the unique identifier of the gym to be verified
     * @return true if the gym is verified successfully; false otherwise
     */
    boolean verifyGym(int gymId);

    /**
     * Verifies the legitimacy of a gym owner.
     *
     * @param gymOwnerId the unique identifier of the gym owner to be verified
     * @return true if the gym owner is verified successfully; false otherwise
     */
    boolean verifyGymOwner(int gymOwnerId);

    /**
     * Retrieves a list of unverified gym owners.
     *
     * @return a list of GymOwner objects representing the gym owners who have not yet been verified
     */
    List<FlipfitGymOwner> getUnverifiedGymOwners();

    /**
     * Retrieves a list of unverified gyms.
     *
     * @return a list of Gym objects representing the gyms that have not yet been verified
     */
    List<FlipfitGymCenter> getUnverifiedGyms();
}
