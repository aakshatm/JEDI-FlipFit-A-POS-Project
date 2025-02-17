package com.flipkart.client;

import java.util.*;

import com.flipkart.bean.FlipfitCustomer;
import com.flipkart.bean.FlipfitGymCenter;
import com.flipkart.business.FlipfitGymCustomerInterface;
import com.flipkart.business.FlipfitGymCustomerService;
import com.flipkart.bean.Booking;
import com.flipkart.business.PaymentInterface;
import com.flipkart.business.PaymentService;
import com.flipkart.bean.Slot;
import com.flipkart.bean.Payment;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;

public class FlipFitGymCustomerMenu {


    private final FlipfitGymCustomerInterface customerService = new FlipfitGymCustomerService();
    private final Scanner scanner = new Scanner(System.in);

    PaymentInterface payerServiceOperations = new PaymentService();

    public boolean userLogin(String email, String password) {
        if (validateUser(email, password)) {
            boolean isLoggedIn = true;

            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = myDateObj.format(myFormatObj);

            FlipfitCustomer customer = customerService.getProfile(email, password);
            System.out.println("Gym customer " + customer.getUserName() + " logged in at " + formattedDate);

            while (isLoggedIn) {
                System.out.println("-------------CUSTOMER MENU-------------");
                System.out.println("Press 1 to view all gyms with slots");
                System.out.println("Press 2 to book slot");
                System.out.println("Press 3 to cancel slot");
                System.out.println("Press 4 to view all bookings");
                System.out.println("Press 5 to view all gyms by area");
                System.out.println("Press 6 to view a slot's availability");
                System.out.println("Press 7 to update your details");
                System.out.println("Press 8 to view Profile");
                System.out.println("Press 9 to logout");
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

                        System.out.println("Enter the following:");

                        System.out.println("FlipfitGymCenter ID: ");
                        int gymId = Integer.parseInt(scanner.nextLine());

                        System.out.println("Slot Time: ");
                        int time = Integer.parseInt(scanner.nextLine());
                        if (processPayments()) {
                            System.out.println("Payment was successful");
                            if (bookSlot(gymId, time, email)) {
                                System.out.println("Slot booked successfully!");
                            }
                        } else {
                            System.out.println("Payment failed. Please try again");
                        }
                        break;
                    case 3:
                        if (!viewAllBookings(email)) {
                            break;
                        }
                        System.out.println("Enter Booking ID: ");
                        int bookingId = Integer.parseInt(scanner.nextLine());

                        if (cancelSlot(bookingId))
                            System.out.println("Booking cancelled successfully!");
                        else
                            System.out.println("Booking cancellation failed.");
                        break;
                    case 4:
                        viewAllBookings(email);
                        break;
                    case 5:

                        System.out.println("Enter location you want to find gyms in: ");

                        String location = scanner.nextLine();
                        List<FlipfitGymCenter> gyms3 = viewAllGymsByArea(location);
                        printGyms(gyms3);
                        break;
                    case 6:
                        System.out.println("Enter gym ID: ");
                        int _gymId = Integer.parseInt(scanner.nextLine());

                        System.out.println("Enter start time: ");
                        int _startTime = Integer.parseInt(scanner.nextLine());
                        int availableSeatCount = customerService.getSeatCount(_gymId, _startTime);
                        if (availableSeatCount == -1) {
                            System.out.println("Seat count is not available. Please try again.");
                            break;
                        }
                        System.out.println("Available seat count: " + availableSeatCount);
                        break;
                    case 7:
                        if (updateUserDetails())
                            System.out.println("FlipfitCustomer updated successfully!");
                        else
                            System.out.println("FlipfitCustomer update was unsuccessful");
                        break;
                    case 8:
                        viewProfile(email, password);
                        break;
                    case 9:
                        isLoggedIn = false;
                        break;
                    default:
                        System.out.println("Wrong choice!");
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * Displays the profile of the customer.
     *
     * @param email    FlipfitCustomer's email address.
     * @param password FlipfitCustomer's password.
     */
    public void viewProfile(String email, String password) {
        FlipfitCustomer profile = customerService.getProfile(email, password);
        profile.displayCustomer();
    }

    /**
     * Validates the user credentials.
     *
     * @param email    FlipfitCustomer's email address.
     * @param password FlipfitCustomer's password.
     * @return true if the user is valid, false otherwise.
     */
    public boolean validateUser(String email, String password) {
        return customerService.validateUser(email, password);
    }

    private void printGyms(List<FlipfitGymCenter> gyms) {
        if (gyms.isEmpty()) {
            System.out.println("No gyms found.");
            return;
        }

        String gymLeftAlignFormat = "| %-5d | %-20s | %-20s | %-40s | %-15s |%n";
        System.out.println("FlipfitGymCenter List:");

        gyms.forEach(gym -> {
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

            gym.getSlots().stream()
                    .filter(slot -> slot.getSeatCount() > 0)
                    .forEach(slot -> System.out.format(slotLeftAlignFormat, slot.getStartTime(), (slot.getStartTime() + 1), slot.getSeatCount()));

            System.out.format("+-----------------+-----------------+-----------------+\n");
            System.out.println();
        });
    }

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

    /**
     * Processes payment by validating card details.
     *
     * @return true if the payment is successful, false otherwise.
     */
    public boolean processPayments() {
        return collectAndValidateCardDetails();
    }

    /**
     * Retrieves all gyms with available slots.
     *
     * @return List of gyms with available slots.
     */
    List<FlipfitGymCenter> viewAllGymsWithSlots() {
        return customerService.viewAllGymsWithSlots();
    }

    /**
     * Books a slot for a gym.
     *
     * @param gymId   The ID of the gym to book the slot in.
     * @param startTime The start time of the slot to book.
     * @param email   The email of the customer booking the slot.
     * @return true if the slot is successfully booked, false otherwise.
     */
    public boolean bookSlot(int gymId, int startTime, String email) {
        return customerService.bookSlot(gymId, startTime, email);
    }


    /**
     * Cancels a booking for a slot.
     *
     * @param bookingId ID of the booking to be cancelled.
     * @return true if the booking is successfully cancelled, false otherwise.
     */
    public boolean cancelSlot(int bookingId) {
        return customerService.cancelSlot(bookingId);
    }


    /**
     * Displays all bookings made by the customer.
     *
     * @param email FlipfitCustomer's email address.
     * @return true if bookings are displayed, false otherwise.
     */
    public boolean viewAllBookings(String email) {
        System.out.println("My Booking: ");
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

            bookings.forEach(booking ->
                    System.out.format(leftAlignFormat, booking.getBookingId(), booking.getBookingStatus(), booking.getTime(), booking.getGymId())
            );
            System.out.format("+------------+---------------+------------+------------+\n");
        }
        return true;
    }


    /**
     * Retrieves a list of gyms based on location.
     *
     * @param location The location to filter gyms by.
     * @return List of gyms located in the specified area.
     */
    List<FlipfitGymCenter> viewAllGymsByArea(String location) {
        return customerService.viewAllGymsByArea(location);
    }


    /**
     * Creates a new customer account.
     */
  
    public void createCustomer() {
        System.out.println("Enter customer details: ");
        System.out.println("Name: ");
        String ownerName = scanner.nextLine();
        System.out.println("Phone Number: ");
        String phoneNo = scanner.nextLine();
        System.out.println("Address: ");
        String address = scanner.nextLine();
        System.out.println("Location: ");
        String location = scanner.nextLine();
        System.out.println("Email: ");
        String ownerEmail = scanner.nextLine();
        System.out.println("Password: ");
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


    /**
     * Updates the details of an existing customer.
     *
     * @return true if customer details are successfully updated, false otherwise.
     */
    public boolean updateUserDetails() {
        System.out.println("Enter customer details: ");
        System.out.println("Email: ");
        String ownerEmail = scanner.nextLine();

        System.out.println("Name: ");
        String ownerName = scanner.nextLine();
        System.out.println("Phone Number: ");
        String phoneNo = scanner.nextLine();

        FlipfitCustomer user = new FlipfitCustomer();
        user.setEmail(ownerEmail);
        user.setUserName(ownerName);
        user.setPhoneNumber(phoneNo);

        return customerService.updateUserDetails(user);
    }


    /**
     * Updates the password of a customer.
     *
     * @param userMail        FlipfitCustomer's email address.
     * @param password        Current password.
     * @param updatedPassword New password.
     * @return true if password is updated successfully, false otherwise.
     */
    public boolean updatePassword(String userMail, String password, String updatedPassword) {
        return customerService.updateGymUserPassword(userMail, password, updatedPassword);
    }
}
