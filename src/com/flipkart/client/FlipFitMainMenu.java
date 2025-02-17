package com.flipkart.client;

import java.util.Scanner;

public class FlipFitMainMenu {

    static Scanner scanner = new Scanner(System.in);

    static FlipFitAdminMenu adminMenu = new FlipFitAdminMenu();
    static FlipFitGymCustomerMenu customerMenu = new FlipFitGymCustomerMenu();
    static FlipFitOwnerMenu ownerMenu = new FlipFitOwnerMenu();



    public static void main(String[] args) {
        String email, password;
        boolean isInApp = true;
        System.out.println("Welcome to FlipFit Application");

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
//                            ownerMenu(scanner);
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

                                do {
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
                                            adminMenu.viewAllGymOwners();
                                            break;
                                        case 4:
                                            adminMenu.viewUnverifiedGyms();
                                            System.out.println("Enter the gym ID to be verified:");
                                            if (adminMenu.verifyGym())
                                                System.out.println("Gym verified successfully!");
                                            else
                                                System.out.println("Gym with given Id does not exist.");
                                            break;
                                        case 5:
                                            adminMenu.viewUnverifiedGymOwners();
                                            System.out.println("Enter the gym owner ID to be verified:");
                                            if (adminMenu.verifyGymOwner())
                                                System.out.println("Gym owner verified successfully!");
                                            else
                                                System.out.println("Gym owner with given ID does not exist.");
                                            break;
                                        case 6:
                                            adminMenu.viewUnverifiedGyms();
                                            break;
                                        case 7:
                                            adminMenu.viewUnverifiedGymOwners();
                                            break;
                                        case 8:
                                            adminMenu.viewProfile();
                                            break;
                                        case 9:
                                            loggedIn = false;
                                            break;
                                    }
                                } while (loggedIn);
                            }
                            break;

                        default:
                            System.out.println("Invalid role entered. Returning to main menu.");
                    }
                    break;
                case 2:
                    customerMenu.createCustomer();
                    break;
                case 3:
                    ownerMenu.createGymOwner();
                    break;
                case 4:
                    System.out.println("-------------Reset Password Page--------------");
                    System.out.println("Select your role:");
                    System.out.println("Press 1 for gym user");
                    System.out.println("Press 2 for gym owner");
                    System.out.println("Press 3 for gym admin");
                    int roleChoice = Integer.parseInt(scanner.nextLine());
                    System.out.println("Please enter your email:");
                    email = scanner.nextLine();
                    System.out.println("Please enter your current password:");
                    password = scanner.nextLine();
                    System.out.println("Please enter new password:");
                    String updatedPassword = scanner.nextLine();


                    switch (roleChoice) {
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
//                            adminMenu.changePassword(updatedPassword);
                            System.out.println("Sorry! You don't have enough rights to do that.");
                            break;
                    }
                    break;
                case 5:
                    isInApp = false;
                    break;
            }
        }
    }
}