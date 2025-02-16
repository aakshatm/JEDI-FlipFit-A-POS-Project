package com.flipkart.client;
import java.util.*;
import com.flipkart.bean.FlipfitCustomer;
import com.flipkart.bean.FlipfitGymCenter;
import com.flipkart.business.FlipfitGymCustomerInterface;
import com.flipkart.business.FlipfitGymCustomerService;
import com.flipkart.bean.Booking;
import com.flipkart.exception.UserNotFoundException;

import java.util.Scanner;

public class FlipFitGymCustomerMenu {
//    System.out.println("\n-- Gym Customer Menu --");
////            System.out.println("1. View Available Gyms");
////            System.out.println("2. Book Slot");
////            System.out.println("3. View My Bookings");
////            System.out.println("4. Cancel Booking");
////            System.out.println("5. Logout");
//
//            System.out.println("1. View Profile"); -> done
//            System.out.println("2. Edit Profile"); -> done
//            System.out.println("3. Find gyms based on city"); // done
//            System.out.println("4. Book Gym Slot"); // done // hume gym center ki list and slot ki list banani padegi main mei hi
//            System.out.println("5. View all Bookings"); // done
//            System.out.println("6. Cancel Bookings"); // done
//            System.out.println("7. Log Out"); // done

    private FlipfitGymCustomerInterface customerService = new FlipfitGymCustomerService();
    private Scanner scanner = new Scanner(System.in);

    public void viewProfile(String email, String password){
        try{
            FlipfitCustomer customer = customerService.getProfile(email, password);
            if (customer == null){
                throw new UserNotFoundException();
            }
            customer.displayCustomer();
        } catch (UserNotFoundException e){

        }

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

    public void viewGymBasedOnLocation(String location){
        List<FlipfitGymCenter> gyms = customerService.viewAllGymsByArea(location);
        for (FlipfitGymCenter gym: gyms){
            gym.display();
        }
    }

    public void bookGymSlot(String email){
        List<FlipfitGymCenter> listOfGyms = customerService.viewAllGymsWithSlots();
        for (FlipfitGymCenter gym: listOfGyms){
            gym.displayWithSlot();
        }
        int gymId, startTime;
        System.out.println("Enter gymID: ");
        System.out.println("Enter startTime: ");
        gymId = scanner.nextInt();
        scanner.nextLine();
        startTime = scanner.nextInt();
        scanner.nextLine();
        boolean bookingDone = customerService.bookSlot(gymId, startTime, email);
        // payment handle later
        if (bookingDone){
            System.out.println("Booking Confirmed");
        }else{
            System.out.println("Booking not done..");
        }
    }

    public void viewAllBookings(String email, String password) {
        FlipfitCustomer customer = customerService.getProfile(email, password);
        int userId = customer.getUserId();

        List<Booking> bookings = customerService.viewAllBookings(userId);
        for (Booking booking : bookings){
            booking.displayBookings();
        }
    }


    public void cancelBooking(){
        int bookingId;
        System.out.println("Please enter the booking ID: ");
        bookingId = scanner.nextInt();
        scanner.nextLine();
        boolean cancelledSuccessfully = customerService.cancelSlot(bookingId);
        if (cancelledSuccessfully){
            System.out.println("Booking Cancelling Successfully..");
            System.out.println("Refund Initiated");
        }
        else{
            System.out.println("Not able to cancel Booking");
        }
    }

    public void logout(){
        System.out.println("Logging out");
    }


}
