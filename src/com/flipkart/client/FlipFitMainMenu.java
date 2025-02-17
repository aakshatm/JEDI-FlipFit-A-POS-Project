package com.flipkart.client;

import com.flipkart.bean.*;
import com.flipkart.business.FlipfitAdminInterface;
import com.flipkart.business.FlipfitGymOwnerInterface;
import com.flipkart.business.FlipfitAdminService;
import com.flipkart.business.FlipfitGymCustomerService;
import com.flipkart.business.FlipfitGymOwnerService;

import java.util.List;
import java.util.Scanner;

/**
 * This class represents the main menu for the FlipFit application.
 * It allows users to log in as a gym customer, gym owner, or gym admin and
 * provides options for various user interactions based on the role selected.
 * It also allows gym customer and gym owner registration, password changes,
 * and navigation to specific menus for their respective roles.
 */
public class FlipFitMainMenu {

    static Scanner scanner = new Scanner(System.in);
    static FlipFitAdminMenu adminMenu = new FlipFitAdminMenu();
    static FlipFitGymCustomerMenu customerMenu = new FlipFitGymCustomerMenu();
    static FlipFitOwnerMenu ownerMenu = new FlipFitOwnerMenu();

    /**
     * The main method that serves as the entry point of the FlipFit application.
     * It displays the main menu and handles user interactions for login, registration, password changes, and role-based navigation.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        String email, password;
        boolean isInApp = true;
        System.out.println("Welcome to FlipFit Application");

        // Main menu loop
        while (isInApp) {
            System.out.println("\nEnter your choice:");
            System.out.println("1. Login");
            System.out.println("2. Registration of Gym Customer");
            System.out.println("3. Registration of Gym Owner");
            System.out.println("4. Change Password");
            System.out.println("5. Exit");

            int optionSelected = Integer.parseInt(scanner.nextLine());

            switch (optionSelected) {
                case 1:
                    // Handle login based on role
                    System.out.println("Enter email:");
                    email = scanner.nextLine();
                    System.out.println("Enter Password:");
                    password = scanner.nextLine();
                    System.out.println("Enter Role (gym customer / gym owner / gym admin):");
                    String role = scanner.nextLine();

                    // Process login based on role
                    switch (role.toLowerCase()) {
                        case "gym customer":
                            if (!customerMenu.userLogin(email, password)) {
                                System.out.println("Invalid credentials! Please enter valid credentials to login");
                                break;
                            }
                            break;
                        case "gym owner":
                            if (!ownerMenu.gymOwnerLogin(email, password)) {
                                System.out.println("Invalid credentials! Please enter valid credentials to login");
                                break;
                            }
                            break;
                        case "gym admin":
                            boolean loggedIn = adminMenu.login(email, password);
                            if (!loggedIn) {
                                System.out.println("Incorrect Email and password. Try Again!!");
                            } else {
                                java.time.LocalDateTime currentDateTime = java.time.LocalDateTime.now();
                                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                String formattedDateTime = currentDateTime.format(formatter);

                                // Print login details
                                System.out.println("Gym Admin " + adminMenu.getAdminName() + " logged in at " + formattedDateTime);

                                // Admin menu loop
                                while (loggedIn) {
                                    System.out.println("-----------------Admin Menu------------------");
                                    System.out.println("Press 1 to view customers");
                                    System.out.println("Press 2 to view all gyms");
                                    System.out.println("Press 3 to view all gym owners");
                                    System.out.println("Press 4 to verify gym");
                                    System.out.println("Press 5 to verify gym owners");
                                    System.out.println("Press 6 to view pending gyms approvals");
                                    System.out.println("Press 7 to view pending gym owners approvals");
                                    System.out.println("Press 8 to view profile");
                                    System.out.println("Press 9 to exit");

                                    optionSelected = Integer.parseInt(scanner.nextLine());

                                    switch (optionSelected) {
                                        case 1:
                                            adminMenu.viewCustomers();
                                            break;
                                        case 2:
                                            adminMenu.viewAllGyms();
                                            break;
                                        case 3:
                                            adminMenu.viewAllGymOwnwers();
                                            break;
                                        case 4:
                                            adminMenu.viewUnverfiedGyms();
                                            System.out.println("Enter the gym ID to be verified:");
                                            if (adminMenu.verifyGym())
                                                System.out.println("Gym verified successfully!");
                                            else
                                                System.out.println("Gym with given Id does not exist.");
                                            break;
                                        case 5:
                                            adminMenu.viewUnverfiedGymOwnwers();
                                            System.out.println("Enter the gym owner ID to be verified:");
                                            if (adminMenu.verifyGymOwnner())
                                                System.out.println("Gym owner verified successfully!");
                                            else
                                                System.out.println("Gym owner with given ID does not exist.");
                                            break;
                                        case 6:
                                            adminMenu.viewUnverfiedGyms();
                                            break;
                                        case 7:
                                            adminMenu.viewUnverfiedGymOwnwers();
                                            break;
                                        case 8:
                                            adminMenu.viewProfile();
                                            break;
                                        case 9:
                                            loggedIn = false;
                                            break;
                                    }
                                    if (!loggedIn) break;
                                }
                            }
                            break;
                        default:
                            System.out.println("Invalid role entered. Returning to main menu.");
                    }
                    break;

                case 2:
                    // Register a new gym customer
                    customerMenu.createCustomer();
                    break;

                case 3:
                    // Register a new gym owner
                    ownerMenu.createGymOwner();
                    break;

                case 4:
                    // Handle password reset
                    System.out.println("-------------Reset Password Page--------------");
                    System.out.println("Select your role:");
                    System.out.println("Press 1 for gym user");
                    System.out.println("Press 2 for gym owner");
                    System.out.println("Press 3 for gym admin");
                    int rolechoice = Integer.parseInt(scanner.nextLine());
                    System.out.println("Please enter your email:");
                    email = scanner.nextLine();
                    System.out.println("Please enter your current password:");
                    password = scanner.nextLine();
                    System.out.println("Please enter new password:");
                    String updatedPassword = scanner.nextLine();

                    // Reset password based on role
                    switch (rolechoice) {
                        case 1:
                            if (!customerMenu.validateUser(email, password)) {
                                System.out.println("Invalid credentials! Please enter valid credentials");
                            } else {
                                if (customerMenu.updatePassword(email, password, updatedPassword))
                                    System.out.println("Password updated successfully!");
                                else
                                    System.out.println("Password doesn't match!");
                            }
                            break;
                        case 2:
                            if (!ownerMenu.verifyGymOwner(email, password)) {
                                System.out.println("Invalid credentials! Please enter valid credentials");
                            } else {
                                if (ownerMenu.updatePassword(email, password, updatedPassword))
                                    System.out.println("Password updated successfully!");
                                else
                                    System.out.println("Password doesn't match!");
                            }
                            break;
                        case 3:
                            System.out.println("Sorry! You don't have enough rights to do that.");
                            break;
                    }
                    break;

                case 5:
                    // Exit the application
                    isInApp = false;
                    break;
            }
        }
    }
}
