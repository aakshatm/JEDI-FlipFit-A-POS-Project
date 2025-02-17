package com.flipkart.business;

import com.flipkart.DAO.FlipFitAdminDAOImplementation;
import com.flipkart.DAO.FlipFitAdminDAOInterface;
import com.flipkart.bean.FlipfitAdmin;
import com.flipkart.bean.FlipfitCustomer;
import com.flipkart.bean.FlipfitGymCenter;
import com.flipkart.bean.FlipfitGymOwner;

import java.util.List;

/**
 * Service class that implements the FlipfitAdminInterface and provides
 * business logic for admin functionalities such as login, logout, profile
 * management, and gym verification.
 */
public class FlipfitAdminService implements FlipfitAdminInterface {

    private static FlipfitAdmin admin;
    private static boolean loginIN;
    FlipFitAdminDAOInterface dao = new FlipFitAdminDAOImplementation();

    /**
     * Constructor that initializes the admin details by fetching from the database
     * through the DAO implementation.
     */
    public FlipfitAdminService() {
        // Fetch data from database and set up admin details
        admin = dao.getAdminDetails();
        loginIN = false;
    }

    /**
     * Logs in the admin by validating the email and password.
     *
     * @param email The admin's email.
     * @param password The admin's password.
     * @return true if login is successful; false otherwise.
     */
    @Override
    public boolean login(String email, String password) {
        if (email.equals(admin.getEmail()) && password.equals(admin.getPassword())) {
            loginIN = true;
            return true;
        }
        loginIN = false;
        return false;
    }

    /**
     * Logs out the admin if currently logged in.
     *
     * @return true if logout is successful; false if no admin is logged in.
     */
    @Override
    public boolean logout() {
        if (loginIN) {
            loginIN = false;
            return true;
        }
        return false;
    }

    /**
     * Views the admin profile if logged in.
     *
     * @return The FlipfitAdmin object representing the admin's profile
     *         if logged in, otherwise null.
     */
    @Override
    public FlipfitAdmin viewProfile() {
        if (loginIN) {
            return admin;
        }
        return null;
    }

    /**
     * Edits the admin profile password.
     *
     * @param adminId The unique ID of the admin.
     * @param password The new password to be set.
     * @return true if the profile is updated successfully; false otherwise.
     */
    @Override
    public boolean editProfile(int adminId, String password) {
        admin = dao.getAdminDetails();
        if (loginIN && adminId == admin.getAdminId()) {
            dao.editProfile(password);
            admin.setPassword(password);
            return true;
        }
        return false;
    }

    /**
     * Views the list of gym owners.
     *
     * @return A list of FlipfitGymOwner objects representing the gym owners.
     */
    @Override
    public List<FlipfitGymOwner> viewGymOwners() {
        return dao.viewGymOwners();
    }

    /**
     * Views the list of gyms.
     *
     * @return A list of FlipfitGymCenter objects representing the gyms.
     */
    @Override
    public List<FlipfitGymCenter> viewGyms() {
        return dao.viewGyms();
    }

    /**
     * Views the list of customers.
     *
     * @return A list of FlipfitCustomer objects representing the customers.
     */
    @Override
    public List<FlipfitCustomer> viewCustomers() {
        return dao.viewCustomers();
    }

    /**
     * Verifies the legitimacy of a gym by its gym ID.
     *
     * @param gymId The unique identifier of the gym to be verified.
     * @return true if the gym is verified successfully; false otherwise.
     */
    @Override
    public boolean verifyGym(int gymId) {
        return dao.verifyGym(gymId);
    }

    /**
     * Verifies the legitimacy of a gym owner by their gym owner ID.
     *
     * @param gymOwnerId The unique identifier of the gym owner to be verified.
     * @return true if the gym owner is verified successfully; false otherwise.
     */
    @Override
    public boolean verifyGymOwner(int gymOwnerId) {
        return dao.verifyGymOwner(gymOwnerId);
    }

    /**
     * Retrieves a list of unverified gym owners.
     *
     * @return A list of FlipfitGymOwner objects representing unverified gym owners.
     */
    @Override
    public List<FlipfitGymOwner> getUnverifiedGymOwners() {
        return dao.getUnverifiedGymOwners();
    }

    /**
     * Retrieves a list of unverified gyms.
     *
     * @return A list of FlipfitGymCenter objects representing unverified gyms.
     */
    @Override
    public List<FlipfitGymCenter> getUnverifiedGyms() {
        return dao.getUnverifiedGyms();
    }
}
