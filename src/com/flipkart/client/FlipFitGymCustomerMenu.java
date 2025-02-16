package com.flipkart.client;
import java.util.*;
import com.flipkart.bean.FlipfitCustomer;
import com.flipkart.business.FlipfitGymCustomerInterface;
import com.flipkart.business.FlipfitGymCustomerService;
import com.flipkart.bean.Booking;

import java.util.Scanner;

public class FlipFitGymCustomerMenu {
//    System.out.println("\n-- Gym Customer Menu --");
////            System.out.println("1. View Available Gyms");
////            System.out.println("2. Book Slot");
////            System.out.println("3. View My Bookings");
////            System.out.println("4. Cancel Booking");
////            System.out.println("5. Logout");
//
//            System.out.println("1. View Profile");
//            System.out.println("2. Edit Profile");
//            System.out.println("3. Find gyms based on city"); // done // assuming ki hum list of gym center main app mei store karenge
//            System.out.println("4. Book Gym Slot"); // done // hume gym center ki list and slot ki list banani padegi main mei hi
//            System.out.println("5. View all Bookings"); // done
//            System.out.println("6. Cancel Bookings"); // done
//            System.out.println("7. Log Out"); // done

    FlipfitGymCustomerInterface customerService = new FlipfitGymCustomerService();
    Scanner scanner = new Scanner(System.in);

    public void viewProfile(String email, String password){
        FlipfitCustomer customer = customerService.getProfile(email, password);
        customer.displayCustomer();
    }

    public void editProfile(String email, String password){
        String username, phoneNumber, address, location;

        System.out.println("Enter your username: ");
        username = scanner.nextLine();

        System.out.println("Enter your phone number: ");
        phoneNumber = scanner.nextLine();

        System.out.println("Enter your address: ");
        address = scanner.nextLine();

        System.out.println("Enter your location: ");
        location = scanner.nextLine();

        FlipfitCustomer customer = customerService.getProfile(email, password);
        int customerId = customer.getUserId();

//        boolean editProfile(int customerId, String email, String password, String username, String phoneNumber, String address, String location);

        customerService.editProfile(customerId, email, password, username, phoneNumber, address, location);
    }

    public void viewAllBookings(String email, String password) {
        FlipfitCustomer customer = customerService.getProfile(email, password);
        int userId = customer.getUserId();

        List<Booking> bookings = customerService.viewAllBookings(userId);
//        bookings.
    }



}
