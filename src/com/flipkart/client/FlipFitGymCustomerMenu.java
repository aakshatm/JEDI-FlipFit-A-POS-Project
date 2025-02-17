package com.flipkart.client;
import java.util.*;
import com.flipkart.bean.FlipfitCustomer;
import com.flipkart.bean.FlipfitGymCenter;
import com.flipkart.business.FlipfitGymCustomerInterface;
import com.flipkart.business.FlipfitGymCustomerService;
import com.flipkart.bean.Booking;
import com.flipkart.business.PaymentInterface;
import com.flipkart.business.PaymentService;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.bean.Slot;
import com.flipkart.bean.Payment;


import java.util.Scanner;

public class FlipFitGymCustomerMenu {


    private FlipfitGymCustomerInterface customerService = new FlipfitGymCustomerService();
    private Scanner scanner = new Scanner(System.in);
    PaymentInterface payerServiceOperations = new PaymentService();

    /**
     * Manages user login and displays the customer menu.
     *
     * @param email    FlipfitCustomer's email address.
     * @param password FlipfitCustomer's password.
     * @return true if login is successful, false otherwise.
     */
    public boolean userLogin(String email, String password) {
        if (validateUser(email, password)) {
            boolean isLoggedIn = true;
            System.out.println( "Customer Login Successful!!" );
            while (isLoggedIn) {
                System.out.println( "-------------CUSTOMER MENU-------------");
                System.out.println("Press 1 to view all gyms with slots");
                System.out.println("Press 2 to book slot");
                System.out.println("Press 3 to cancel slot");
                System.out.println("Press 4 to view all bookings");
                System.out.println("Press 5 to view all gyms by area");
                System.out.println("Press 6 to view a slot's availability");
                System.out.println("Press 7 to update your details");
                System.out.println("Press 8 to view Profile");
                System.out.println("Press 9 to logout" );
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        List<FlipfitGymCenter> gyms1 = viewAllGymsWithSlots();
                        printGyms(gyms1);
                        break;
                    case 2:
                        List<FlipfitGymCenter> gyms2 = viewAllGymsWithSlots();
                        printGyms(gyms2);
                        if (gyms2.isEmpty()) {
                            break;
                        }
                        System.out.println(  "Enter the following: " );
                        System.out.println( "FlipfitGymCenter ID: " );
                        int gymId = Integer.parseInt(scanner.nextLine());

                        System.out.println( "Slot Time: " );
                        int time = Integer.parseInt(scanner.nextLine());
                        if (processPayments()) {
                            System.out.println("Payment was successful");
                            if (bookSlot(gymId, time, email)) {
                                System.out.println( "Slot booked successfully!" );
                            }
                        } else {
                            System.out.println("Payment failed. Please try again");
                        }
                        break;
                    case 3:
                        if (!viewAllBookings(email)) {
                            break;
                        }
                        System.out.println( "Enter Booking ID: " );
                        int bookingId = Integer.parseInt(scanner.nextLine());

                        if (cancelSlot(bookingId))
                            System.out.println( "Booking cancelled successfully!" );
                        else
                            System.out.println( "Booking cancellation failed." );
                        break;
                    case 4:
                        viewAllBookings(email);
                        break;
                    case 5:
                        System.out.println( "Enter location you want find gyms in: " );
                        String location = scanner.nextLine();
                        List<FlipfitGymCenter> gyms3 = viewAllGymsByArea(location);
                        printGyms(gyms3);
                        break;
                    case 6:
                        System.out.println( "Enter gym ID: " );
                        int _gymId = Integer.parseInt(scanner.nextLine());

                        System.out.println( "Enter start time: " );
                        int _startTime = Integer.parseInt(scanner.nextLine());
                        int availableSeatCount = customerService.getSeatCount(_gymId, _startTime);
                        if (availableSeatCount == -1) {
                            System.out.println("Seat count is not available. Please try again." );
                            break;
                        }
                        System.out.println( "Available seat count: " + availableSeatCount );
                        break;
                    case 7:
                        if (updateUserDetails())
                            System.out.println( "FlipfitCustomer updated successfully!" );
                        else
                            System.out.println( "FlipfitCustomer update was unsuccessful" );
                        break;
                    case 8:
                        viewProfile(email, password);
                    case 9:
                        isLoggedIn = false;
                        break;
                    default:
                        System.out.println(  "Wrong choice!" );
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public void viewProfile(String email, String password){
        FlipfitCustomer profile = customerService.getProfile(email, password);
        profile.displayCustomer();
    }

    public boolean validateUser(String email, String password) {
        return customerService.validateUser(email, password);
    }

    /**
     * Prints the list of gyms and their slots.
     *
     * @param gyms List of gyms to be printed.
     */
    private void printGyms(List<FlipfitGymCenter> gyms) {
        if (gyms.isEmpty()) {
            System.out.println("No gyms found.");
            return;
        }

        String gymLeftAlignFormat = "| %-5d | %-20s | %-20s | %-40s | %-15s |%n";
        System.out.println("FlipfitGymCenter List:");

        for (FlipfitGymCenter gym : gyms) {
            System.out.format("+-------+----------------------+----------------------+------------------------------------------+------------------+\n");
            System.out.format("| FlipfitGymCenter ID|     Name             |     Location         |           Address                         |     Status       |\n");
            System.out.format("+-------+----------------------+----------------------+------------------------------------------+------------------+\n");
            System.out.format(gymLeftAlignFormat, gym.getGymId(), gym.getGymName(), gym.getLocation(), gym.getGymAddress(), gym.getStatus());
            System.out.format("+-------+----------------------+----------------------+------------------------------------------+------------------+\n");

            System.out.println("Slot List:");
            String slotLeftAlignFormat = "| %-15s | %-15s | %-15d |%n";
            System.out.format("+-----------------+-----------------+-----------------+\n");
            System.out.format("| Start Time      |   End Time      | Remaining Seats |\n");
            System.out.format("+-----------------+-----------------+-----------------+\n");

            for (Slot slot : gym.getSlots()) {
                if (slot.getSeatCount() > 0)
                    System.out.format(slotLeftAlignFormat, slot.getStartTime(), (slot.getStartTime() + 1), slot.getSeatCount());
            }
            System.out.format("+-----------------+-----------------+-----------------+\n");
            System.out.println();
        }
    }

    /**
     * Collects and validates card details for payment.
     *
     * @return true if card details are valid, false otherwise.
     */
    public boolean collectAndValidateCardDetails() {
        System.out.print("Enter card number: ");
        String cardNumber = scanner.nextLine();
       
        System.out.print("Enter expiry date (MM/YY): ");
        String expiryDate = scanner.nextLine();
        
        System.out.print("Enter cardholder's name: ");
        String name = scanner.nextLine();

        System.out.print("Enter CVV: ");
        String cvv = scanner.nextLine();
        

        Payment payments = new Payment();
        payments.setCardNumber(cardNumber);
        payments.setExpiryDate(expiryDate);
        payments.setName(name);
        payments.setCvv(cvv);

        return payerServiceOperations.validateCardDetails(payments);
    }

    public boolean processPayments() {
        return collectAndValidateCardDetails();
    }

    List<FlipfitGymCenter> viewAllGymsWithSlots() {
        return customerService.viewAllGymsWithSlots();
    }

    public boolean bookSlot(int gymId, int startTime, String email) {
        return customerService.bookSlot(gymId, startTime, email);
    }

    /**
     * Cancels a booking.
     *
     * @param bookingId ID of the booking to be cancelled.
     * @return true if booking is cancelled successfully, false otherwise.
     */
    public boolean cancelSlot(int bookingId) {
        return customerService.cancelSlot(bookingId);
    }

    /// Displays all bookings made by the user.
    ///
    /// @param email FlipfitCustomer's email address.
    public boolean viewAllBookings(String email) {
        System.out.println( "My Booking: " );
        int userId = customerService.getUserIdByEmail(email);
        if (userId == -1) {
            System.out.println("No such user exists with email: " + email);
            return false;
        }
        List<Booking> bookings = customerService.viewAllBookings(userId);
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return false;
        } else {
            String leftAlignFormat = "| %-10s | %-15s | %-10s | %-10s | %n";
            System.out.format("+------------+---------------+------------+------------+\n");
            System.out.format("| Booking ID |     Status    |    Time    |  FlipfitGymCenter ID    |\n");
            System.out.format("+------------+---------------+------------+------------+\n");

            for (Booking booking : bookings) {
                System.out.format(leftAlignFormat, booking.getBookingId(), booking.getBookingStatus(), booking.getTime(), booking.getGymId());
            }
            System.out.format("+------------+---------------+------------+------------+\n");
        }
        return true;
    }

    /// Retrieves the list of all gyms by a specified area.
    ///
    /// @param location Location to filter gyms.
    /// @return List of gyms in the specified area.
    List<FlipfitGymCenter> viewAllGymsByArea(String location) {
        return customerService.viewAllGymsByArea(location);
    }

    /// Creates a new customer account.
    public void createCustomer() {
        System.out.println( "Enter customer details: " );
//        Name
        System.out.println( "Name: " );
        String ownerName = scanner.nextLine();
//        Mobile Number
        System.out.println( "Phone Number: " );
        String phoneNo = scanner.nextLine();
        
//        Address
        System.out.println( "Address: " );
        String address = scanner.nextLine();
        

//        Location
        System.out.println( "Location: " );
        String location = scanner.nextLine();
        
//        Email
        System.out.println("Email: ");
        String ownerEmail = scanner.nextLine();
       
//        Password
        System.out.println( "Password: " );
        String password = scanner.nextLine();
        





        FlipfitCustomer user = new FlipfitCustomer();
        user.setUserName(ownerName);
        user.setPhoneNumber(phoneNo);
        user.setAddress(address);
        user.setLocation(location);
        user.setEmail(ownerEmail);

        user.setPassword(password);


        if (customerService.createUser(user))
            System.out.println("FlipfitCustomer created!");
        else
            System.out.println("FlipfitCustomer not created!");
    }

    /// Updates the details of an existing user.
    ///
    /// @return true if user details are updated successfully, false otherwise.
    public boolean updateUserDetails() {
        System.out.println( "Enter customer details: " );
        System.out.println( "Email: " );
        String ownerEmail = scanner.nextLine();

        System.out.println( "Name: " );
        String ownerName = scanner.nextLine();
        System.out.println( "Phone Number: " );
        String phoneNo = scanner.nextLine();


        FlipfitCustomer user = new FlipfitCustomer();
        user.setEmail(ownerEmail);
        user.setUserName(ownerName);
        user.setPhoneNumber(phoneNo);

        return customerService.updateUserDetails(user);
    }

    /// Updates the password of a user.
    ///
    /// @param userMail        FlipfitCustomer's email address.
    /// @param password        Current password.
    /// @param updatedPassword New password.
    /// @return true if password is updated successfully, false otherwise.
    public boolean updatePassword(String userMail, String password, String updatedPassword) {
        return customerService.updateGymUserPassword(userMail, password, updatedPassword);
    }
}


//    public void viewProfile(String email, String password){
//        try{
//            FlipfitCustomer customer = customerService.getProfile(email, password);
//            if (customer == null){
//                throw new UserNotFoundException();
//            }
//            customer.displayCustomer();
//        } catch (UserNotFoundException e){
//
//        }
//
//    }
//
//    public void editProfile(String email, String password){
//        String username, phoneNumber, address, location;
//
//        System.out.println("Enter your username: ");
//        username = scanner.nextLine();
//
//        System.out.println("Enter your phone number: ");
//        phoneNumber = scanner.nextLine();
//
//        System.out.println("Enter your address: ");
//        address = scanner.nextLine();
//
//        System.out.println("Enter your location: ");
//        location = scanner.nextLine();
//
//        FlipfitCustomer customer = customerService.getProfile(email, password);
//        int customerId = customer.getUserId();
//
////        boolean editProfile(int customerId, String email, String password, String username, String phoneNumber, String address, String location);
//
//        customerService.editProfile(customerId, email, password, username, phoneNumber, address, location);
//    }
//
//    public void viewGymBasedOnLocation(String location){
//        List<FlipfitGymCenter> gyms = customerService.viewAllGymsByArea(location);
//        for (FlipfitGymCenter gym: gyms){
//            gym.display();
//        }
//    }
//
//    public void bookGymSlot(String email){
//        List<FlipfitGymCenter> listOfGyms = customerService.viewAllGymsWithSlots();
//        for (FlipfitGymCenter gym: listOfGyms){
//            gym.displayWithSlot();
//        }
//        int gymId, startTime;
//        System.out.println("Enter gymID: ");
//        System.out.println("Enter startTime: ");
//        gymId = scanner.nextInt();
//        scanner.nextLine();
//        startTime = scanner.nextInt();
//        scanner.nextLine();
//        boolean bookingDone = customerService.bookSlot(gymId, startTime, email);
//        // payment handle later
//        if (bookingDone){
//            System.out.println("Booking Confirmed");
//        }else{
//            System.out.println("Booking not done..");
//        }
//    }
//
//    public void viewAllBookings(String email, String password) {
//        FlipfitCustomer customer = customerService.getProfile(email, password);
//        int userId = customer.getUserId();
//
//        List<Booking> bookings = customerService.viewAllBookings(userId);
//        for (Booking booking : bookings){
//            booking.displayBookings();
//        }
//    }
//
//
//    public void cancelBooking(){
//        int bookingId;
//        System.out.println("Please enter the booking ID: ");
//        bookingId = scanner.nextInt();
//        scanner.nextLine();
//        boolean cancelledSuccessfully = customerService.cancelSlot(bookingId);
//        if (cancelledSuccessfully){
//            System.out.println("Booking Cancelling Successfully..");
//            System.out.println("Refund Initiated");
//        }
//        else{
//            System.out.println("Not able to cancel Booking");
//        }
//    }
//
//    public void logout(){
//        System.out.println("Logging out");
//    }



