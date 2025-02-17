package com.flipkart.DAO;

import com.flipkart.bean.FlipfitAdmin;
import com.flipkart.bean.FlipfitGymCenter;
import com.flipkart.bean.FlipfitGymOwner;
import com.flipkart.bean.FlipfitCustomer;

import java.util.List;

public interface FlipFitAdminDAOInterface {

    /**
     * Retrieves the details of the admin.
     * @return A FlipfitAdmin object containing the admin details such as ID, name, password, phone number, and email.
     */
    FlipfitAdmin getAdminDetails();

    /**
     * Allows the admin to edit their profile password.
     * @param password The new password to be set for the admin.
     * @return True if the password was updated successfully, otherwise false.
     */
    boolean editProfile(String password);

    /**
     * Admin can view all registered gym owners.
     * @return A list of FlipfitGymOwner objects representing all registered gym owners. If no gym owners are found, an empty list is returned.
     */
    List<FlipfitGymOwner> viewGymOwners();

    /**
     * Admin can view all registered gyms.
     * @return A list of FlipfitGymCenter objects representing all registered gyms. If no gyms are found, an empty list is returned.
     */
    List<FlipfitGymCenter> viewGyms();

    /**
     * Admin can view all registered users.
     * @return A list of FlipfitCustomer objects representing all registered users. If no users are found, an empty list is returned.
     */
    List<FlipfitCustomer> viewCustomers();

    /**
     * Admin can verify a gym, updating its status to "verified."
     * @param gymId The ID of the gym to be verified.
     * @return True if the gym with the specified ID is found and successfully verified, otherwise false.
     */
    boolean verifyGym(int gymId);

    /**
     * Admin can verify a gym owner, updating their status to "verified."
     * @param gymOwnerId The ID of the gym owner to be verified.
     * @return True if the gym owner with the specified ID is found and successfully verified, otherwise false.
     */
    boolean verifyGymOwner(int gymOwnerId);

    /**
     * Retrieves a list of unverified gym owners.
     * @return A list of unverified FlipfitGymOwner objects.
     */
    List<FlipfitGymOwner> getUnverifiedGymOwners();

    /**
     * Retrieves a list of unverified gyms.
     * @return A list of unverified FlipfitGymCenter objects.
     */
    List<FlipfitGymCenter> getUnverifiedGyms();
}
