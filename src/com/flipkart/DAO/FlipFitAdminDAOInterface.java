package com.flipkart.DAO;

import com.flipkart.bean.FlipfitAdmin;
import com.flipkart.bean.FlipfitGymCenter;
import com.flipkart.bean.FlipfitGymOwner;
import com.flipkart.bean.FlipfitCustomer;

import java.util.List;

public interface FlipFitAdminDAOInterface {

    FlipfitAdmin getAdminDetails();

    boolean editProfile(String password);

    /**
     * Admin Can View All the FlipfitGymCenter Owners
     *@return A list of GymOwner objects representing all registered gym owners. If no gym owners are found, an empty list is returned.*/
    List<FlipfitGymOwner> viewGymOwners();
    /**
     * Admin Can View All the Gyms
     * @return A list of FlipfitGymCenter objects representing all registered gyms. If no gyms are found, an empty list is returned.
     */
    List<FlipfitGymCenter> viewGyms();
    /**
     * Admin Can View All the Users
     * @return A list of User objects representing all registered users. If no users are found, an empty list is returned.
     */
    List<FlipfitCustomer> viewCustomers();
    /**
     * Admin Can Verify the Gyms and change their Status Like Verified Profile
     * @return  True if the gym with the specified ID exists and is valid otherwise false if the gym is not found or is invalid.
     * @param gymId- The ID of the gym to be verified
     */
    boolean verifyGym(int gymId);
    /**
     * Admin Can Verify the FlipfitGymCenter Owners and change their Status Like Verified Profile
     * @return  True if the gym owner with the specified ID is valid and exists otherwise false if the gym owner is not found or is invalid.
     * @param gymOwnerId - The ID of the gym owner to be verified
     */
    boolean verifyGymOwner(int gymOwnerId);
    /**
     * Get a list of unverified gyms
     * @return List of unverified gyms
     */
    List<FlipfitGymOwner> getUnverifiedGymOwners();
    /**
     * Get a list of unverified gym owners
     * @return List of unverified gym owners
     */
    List<FlipfitGymCenter> getUnverifiedGyms();
}
