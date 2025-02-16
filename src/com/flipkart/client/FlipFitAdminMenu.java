package com.flipkart.client;
import com.flipkart.bean.FlipfitAdmin;
import com.flipkart.bean.FlipfitCustomer;
import com.flipkart.bean.FlipfitGymCenter;
import com.flipkart.bean.FlipfitGymOwner;
import com.flipkart.business.*;

import java.util.List;
import java.util.Scanner;

public class FlipFitAdminMenu {
//    System.out.println("1. View All Registered Gyms");
//            System.out.println("2. View All Users");
//            System.out.println("3. View pending approvals");
//            System.out.println("4. Approve/Reject Gym Owner Registration");
//            System.out.println("5. View All Bookings");
//            System.out.println("6. Deactivate Gym");
//            System.out.println("7. Logout");

    private FlipfitAdminInterface adminService = new FlipfitAdminService();
    private Scanner scanner = new Scanner(System.in);

    public void viewCustomers(){
        List<FlipfitCustomer> customers = adminService.viewCustomers();
        for (FlipfitCustomer customer: customers){
            customer.displayCustomer();
        }
    }

    public void viewUnverfiedGymOwnwers(){
        List<FlipfitGymOwner> gymOwnwers =  adminService.getUnverifiedGymOwners();

    }

    public void viewUnverfiedGyms(){
        List<FlipfitGymCenter> gymCenters =  adminService.getUnverifiedGyms();

    }

    public void viewProfile(){
        FlipfitAdmin admin = adminService.viewProfile();
        admin.display();
    }

    public void changePassword(String password){
        FlipfitAdmin admin = adminService.viewProfile();

        boolean edited = adminService.editProfile(admin.getAdminId(), password);
        if (edited){
            System.out.println("Successfully changed password");
        }
    }

    public void viewAllGymOwnwers(){
        List<FlipfitGymOwner> ownwers = adminService.viewGymOwners();
        for(FlipfitGymOwner owner : ownwers){
            owner.display();
        }
    }

    public void viewAllGyms(){
        List<FlipfitGymCenter> gyms = adminService.viewGyms();
        for(FlipfitGymCenter gym : gyms){
            gym.display();
        }
    }

    public void verifyGym(){
        viewUnverfiedGyms();
        int gymId;
        System.out.println("Please enter the gymId to verify: ");
        gymId = scanner.nextInt();
        scanner.nextLine();
        boolean approved = adminService.verifyGym(gymId);
        if (approved){
            System.out.println("Successfully verified..");
        }else{
            System.out.println("Couldn't verify.. ");
        }
    }

    public void verifyGymOwnner(){
        viewUnverfiedGymOwnwers();
        int ownerId;
        System.out.println("Please enter the ownerId to verify: ");
        ownerId = scanner.nextInt();
        scanner.nextLine();
        boolean approved = adminService.verifyGym(ownerId);
        if (approved){
            System.out.println("Successfully verified..");
        }else{
            System.out.println("Couldn't verify.. ");
        }
    }

    public void logout(){
        System.out.println("Logging out..");
    }
}
