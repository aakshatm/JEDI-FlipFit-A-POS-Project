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

    public boolean login(String email, String password) {
        return adminService.login(email, password);
    }

    public void viewCustomers() {
        List<FlipfitCustomer> customers = adminService.viewCustomers();
        customers.forEach(FlipfitCustomer::displayCustomer); // Using forEach with method reference
    }

    public void viewUnverifiedGymOwners() {
        List<FlipfitGymOwner> gymOwners = adminService.getUnverifiedGymOwners();
        gymOwners.forEach(FlipfitGymOwner::display); // Using forEach with method reference
    }

    public void viewUnverifiedGyms() {
        List<FlipfitGymCenter> gymCenters = adminService.getUnverifiedGyms();
        gymCenters.forEach(FlipfitGymCenter::display); // Using forEach with method reference
    }

    public String getAdminName() {
        FlipfitAdmin admin = adminService.viewProfile();
        return admin.getName();
    }

    public void viewProfile() {
        FlipfitAdmin admin = adminService.viewProfile();
        admin.display();
    }

    public void changePassword(String password) {
        FlipfitAdmin admin = adminService.viewProfile();
        boolean edited = adminService.editProfile(admin.getAdminId(), password);
        if (edited) {
            System.out.println("Successfully changed password");
        }
    }

    public void viewAllGymOwners() {
        List<FlipfitGymOwner> owners = adminService.viewGymOwners();
        owners.forEach(FlipfitGymOwner::display); // Using forEach with method reference
    }

    public void viewAllGyms() {
        List<FlipfitGymCenter> gyms = adminService.viewGyms();
        gyms.forEach(FlipfitGymCenter::display); // Using forEach with method reference
    }

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

    public void logout() {
        System.out.println("Logging out..");
    }

    // Optional: Stream usage for viewing all gym owners
    public void viewAllGymOwnersUsingStream() {
        List<FlipfitGymOwner> owners = adminService.viewGymOwners();
        owners
                .forEach(FlipfitGymOwner::display); // Using stream with forEach to display gym owners
    }

    // Optional: Stream usage for displaying all gyms
    public void viewAllGymsUsingStream() {
        List<FlipfitGymCenter> gyms = adminService.viewGyms();
        gyms
                .forEach(FlipfitGymCenter::display); // Using stream with forEach to display gyms
    }
}
