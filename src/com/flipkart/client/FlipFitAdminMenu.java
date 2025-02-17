package com.flipkart.client;

import com.flipkart.bean.FlipfitAdmin;
import com.flipkart.bean.FlipfitCustomer;
import com.flipkart.bean.FlipfitGymCenter;
import com.flipkart.bean.FlipfitGymOwner;
import com.flipkart.business.*;

import java.util.List;
import java.util.Scanner;

public class FlipFitAdminMenu {

    private FlipfitAdminInterface adminService = new FlipfitAdminService();
    private Scanner scanner = new Scanner(System.in);

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
    public void viewCustomers(){
        List<FlipfitCustomer> customers = adminService.viewCustomers();
        for (FlipfitCustomer customer: customers){
            customer.displayCustomer();
        }
    }

    /**
     * Displays the list of unverified gym owners.
     */
    public void viewUnverfiedGymOwnwers(){
        List<FlipfitGymOwner> gymOwnwers =  adminService.getUnverifiedGymOwners();
        for (FlipfitGymOwner owner: gymOwnwers){
            owner.display();
        }
    }

    /**
     * Displays the list of unverified gyms.
     */
    public void viewUnverfiedGyms(){
        List<FlipfitGymCenter> gymCenters =  adminService.getUnverifiedGyms();
        for(FlipfitGymCenter gym: gymCenters){
            gym.display();
        }
    }

    /**
     * Retrieves the name of the admin.
     *
     * @return The name of the admin.
     */
    public String getAdminName(){
        FlipfitAdmin admin = adminService.viewProfile();
        return admin.getName();
    }

    /**
     * Displays the profile details of the admin.
     */
    public void viewProfile(){
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
        if (edited){
            System.out.println("Successfully changed password");
        }
    }

    /**
     * Displays the list of all gym owners.
     */
    public void viewAllGymOwnwers(){
        List<FlipfitGymOwner> ownwers = adminService.viewGymOwners();
        for(FlipfitGymOwner owner : ownwers){
            owner.display();
        }
    }

    /**
     * Displays the list of all gyms.
     */
    public void viewAllGyms(){
        List<FlipfitGymCenter> gyms = adminService.viewGyms();
        for(FlipfitGymCenter gym : gyms){
            gym.display();
        }
    }

    /**
     * Verifies a gym by its ID.
     * Displays the list of unverified gyms and prompts for gym ID.
     *
     * @return true if the gym is verified successfully, false otherwise.
     */
    public boolean verifyGym(){
        viewUnverfiedGyms();
        int gymId;
        System.out.println("Please enter the gymId to verify: ");
        gymId = scanner.nextInt();
        scanner.nextLine();
        boolean approved = adminService.verifyGym(gymId);
        if (approved){
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
    public boolean verifyGymOwnner(){
        viewUnverfiedGymOwnwers();
        int ownerId;
        System.out.println("Please enter the ownerId to verify: ");
        ownerId = scanner.nextInt();
        scanner.nextLine();
        boolean approved = adminService.verifyGym(ownerId);
        if (approved){
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
    public void logout(){
        System.out.println("Logging out..");
    }
}
