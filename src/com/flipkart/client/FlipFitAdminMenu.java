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

        List<FlipfitCustomer> users = adminService.viewCustomers();
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        String leftAlignFormat = "| %-8s | %-20s | %-30s | %-15s | %-20s | %-40s |%n";
        System.out.format("+----------+----------------------+-------------------------------+---------------+--------------------+--------------------------------------+%n");
        System.out.format("| User ID  |     Name             | Email                         | Phone Number  | Location           | Address                              |%n");
        System.out.format("+----------+----------------------+-------------------------------+---------------+--------------------+--------------------------------------+%n");

        for (FlipfitCustomer user : users) {
            System.out.format(leftAlignFormat, user.getUserId(), user.getUserName(), user.getEmail(), user.getPhoneNumber(), user.getLocation(), user.getAddress());
        }
        System.out.format("+----------+----------------------+-------------------------------+---------------+--------------------+--------------------------------------+%n");
    }

   /**
     * Displays the list of unverified gym owners.
     */
    public void viewUnverifiedGymOwners() {
        List<FlipfitGymOwner> gymOwnerList = adminService.getUnverifiedGymOwners();
        if (gymOwnerList.isEmpty()) {
            System.out.println("No unverified gym owners found.");
            return;
        }

        String leftAlignFormat = "| %-5d | %-10d | %-20s | %-20s | %-15s |%n";
        System.out.format("+-------+----------+----------------------+----------------------+------------------+%n");
        System.out.format("| S.No  | Owner ID |     Owner Name       |     Email            |     Status       |%n");
        System.out.format("+-------+----------+----------------------+----------------------+------------------+%n");

        int gymOwnerCounter = 1;
        for (FlipfitGymOwner gymOwner : gymOwnerList) {
            System.out.format(leftAlignFormat, gymOwnerCounter, gymOwner.getOwnerId(), gymOwner.getOwnerName(), gymOwner.getOwnerEmail(), gymOwner.getVerificationStatus());
            gymOwnerCounter++;
        }
        System.out.format("+-------+----------+----------------------+----------------------+------------------+%n");
    }
      
    /**
     * Displays the list of unverified gyms.
     */
    public void viewUnverifiedGyms() {
        List<FlipfitGymCenter> gyms = adminService.getUnverifiedGyms();
        if (gyms.isEmpty()) {
            System.out.println("No unverified gyms found.");
            return;
        }

        String leftAlignFormat = "| %-5d | %-20s | %-5d | %-40s | %-20s | %-15s |%n";
        System.out.format("+-------+----------------------+--------+------------------------------------------+----------------------+------------------+%n");
        System.out.format("| S.No  |     Name             | Gym ID |           Address                        |   Location           |     Status       |%n");
        System.out.format("+-------+----------------------+--------+------------------------------------------+----------------------+------------------+%n");

        int gymCounter = 1;
        for (FlipfitGymCenter g : gyms) {
            System.out.format(leftAlignFormat, gymCounter, g.getGymName(), g.getGymId(), g.getGymAddress(), g.getLocation(), g.getStatus());
            gymCounter++;
        }
        System.out.format("+-------+----------------------+--------+------------------------------------------+----------------------+------------------+%n");
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
        List<FlipfitGymOwner> gymOwners = adminService.viewGymOwners();
        if (gymOwners.isEmpty()) {
            System.out.println("No gym owners found.");
            return;
        }

        String leftAlignFormat = "| %-13s | %-20s | %-30s | %-15s | %-10s | %-20s | %-20s | %-20s |%n";
        System.out.format("+---------------+----------------------+-------------------------------+---------------+------------+----------------------+----------------------+----------------------+%n");
        System.out.format("| Gym Owner ID  |     Name             | Email                         | Phone Number  | GST        | National ID          | Verification Status  | PAN                  |%n");
        System.out.format("+---------------+----------------------+-------------------------------+---------------+------------+----------------------+----------------------+----------------------+%n");

        for (FlipfitGymOwner gymOwner : gymOwners) {
            System.out.format(leftAlignFormat, gymOwner.getOwnerId(), gymOwner.getOwnerName(), gymOwner.getOwnerEmail(), gymOwner.getPhoneNo(), gymOwner.getGST(), gymOwner.getNationalId(), gymOwner.getVerificationStatus(), gymOwner.getPAN());
        }
        System.out.format("+---------------+----------------------+-------------------------------+---------------+------------+----------------------+----------------------+----------------------+%n");
    }
    /**
     * Displays the list of all gyms.
     */
    public void viewAllGyms(){
        List<FlipfitGymCenter> gyms = adminService.viewGyms();
        if (gyms.isEmpty()) {
            System.out.println("No gyms found.");
            return;
        }

        String leftAlignFormat = "| %-6s | %-20s | %-40s | %-20s | %-10s | %-10s |%n";
        System.out.format("+--------+----------------------+----------------------------------------+----------------------+----------+----------+%n");
        System.out.format("| Gym ID |     Name             | Address                                | Location             | Owner ID | Status   |%n");
        System.out.format("+--------+----------------------+----------------------------------------+----------------------+----------+----------+%n" );

        for (FlipfitGymCenter gym : gyms) {
            System.out.format(leftAlignFormat, gym.getGymId(), gym.getGymName(), gym.getGymAddress(), gym.getLocation(), gym.getOwnerId(), gym.getStatus());
        }
        System.out.format("+--------+----------------------+----------------------------------------+----------------------+----------+----------+%n");
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