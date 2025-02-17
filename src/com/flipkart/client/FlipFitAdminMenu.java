package com.flipkart.client;

import com.flipkart.bean.FlipfitAdmin;
import com.flipkart.bean.FlipfitCustomer;
import com.flipkart.bean.FlipfitGymCenter;
import com.flipkart.bean.FlipfitGymOwner;
import com.flipkart.business.*;

import java.util.List;
import java.util.Scanner;

public class FlipFitAdminMenu {

    private final FlipfitAdminInterface adminService = new FlipfitAdminService();
    private final Scanner scanner = new Scanner(System.in);


    /**
     * Logs in the admin with the provided credentials.
     *
     * @param email The email address of the admin.
     * @param password The password of the admin.
     * @return true if login is successful, false otherwise.
     */
    public boolean login(String email, String password){
        return adminService.login(email, password);
    }

    /**
     * Displays the details of all customers.
     */
    public void viewCustomers() {

        List<FlipfitCustomer> customers = adminService.viewCustomers();
        customers.forEach(FlipfitCustomer::displayCustomer); // Using forEach with method reference
    }

   /**
     * Displays the list of unverified gym owners.
     */
    public void viewUnverifiedGymOwners() {
        List<FlipfitGymOwner> gymOwners = adminService.getUnverifiedGymOwners();
        gymOwners.forEach(FlipfitGymOwner::display); // Using forEach with method reference
    }
      
    /**
     * Displays the list of unverified gyms.
     */
    public void viewUnverifiedGyms() {
        List<FlipfitGymCenter> gymCenters = adminService.getUnverifiedGyms();
        gymCenters.forEach(FlipfitGymCenter::display); // Using forEach with method reference
    }
  
    /**
     * Retrieves the name of the admin.
     *
     * @return The name of the admin.
     */
    public String getAdminName() {
        FlipfitAdmin admin = adminService.viewProfile();
        return admin.getName();
    }

    /**
     * Displays the profile details of the admin.
     */
    public void viewProfile() {
        FlipfitAdmin admin = adminService.viewProfile();
        admin.display();
    }


    /**
     * Changes the admin password to the provided new password.
     *
     * @param password The new password to set for the admin.
     */
    public void changePassword(String password){
        FlipfitAdmin admin = adminService.viewProfile();
        boolean edited = adminService.editProfile(admin.getAdminId(), password);
        if (edited) {
            System.out.println("Successfully changed password");
        }
    }


    /**
     * Displays the list of all gym owners.
     */
    public void viewAllGymOwners() {
        List<FlipfitGymOwner> owners = adminService.viewGymOwners();
        owners.forEach(FlipfitGymOwner::display); // Using forEach with method reference
    }
    /**
     * Displays the list of all gyms.
     */
    public void viewAllGyms(){
        List<FlipfitGymCenter> gyms = adminService.viewGyms();
        gyms.forEach(FlipfitGymCenter::display); // Using forEach with method reference
    }


    /**
     * Verifies a gym by its ID.
     * Displays the list of unverified gyms and prompts for gym ID.
     *
     * @return true if the gym is verified successfully, false otherwise.
     */
    public boolean verifyGym() {
        viewUnverifiedGyms();
        System.out.println("Please enter the gymId to verify: ");
        int gymId = scanner.nextInt();
        scanner.nextLine();
        boolean approved = adminService.verifyGym(gymId);
        if (approved) {
            System.out.println("Successfully verified..");
            return true;
        } else {
            System.out.println("Couldn't verify.. ");
            return false;
        }
    }


    /**
     * Verifies a gym owner by their ID.
     * Displays the list of unverified gym owners and prompts for owner ID.
     *
     * @return true if the gym owner is verified successfully, false otherwise.
     */
    public boolean verifyGymOwner() {
        viewUnverifiedGymOwners();
        System.out.println("Please enter the ownerId to verify: ");
        int ownerId = scanner.nextInt();
        scanner.nextLine();
        boolean approved = adminService.verifyGym(ownerId);
        if (approved) {
            System.out.println("Successfully verified..");
            return true;
        } else {
            System.out.println("Couldn't verify.. ");
            return false;
        }
    }

    /**
     * Logs out the admin.
     * Displays a logout message.
     */
    public void logout() {
        System.out.println("Logging out..");
    }
}