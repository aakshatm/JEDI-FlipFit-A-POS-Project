package com.flipkart.client;

import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Welcome message
        System.out.println("Welcome to FlipFit Application");

        do {
            // Display main menu
            System.out.println("\nEnter your choice:");
            System.out.println("1. Login");
            System.out.println("2. Registration of Gym Customer");
            System.out.println("3. Registration of Gym Owner");
            System.out.println("4. Change Password");
            System.out.println("5. Exit");

            // Read user input
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    registerCustomer(scanner);
                    break;
                case 3:
                    registerGymOwner(scanner);
                    break;
                case 4:
                    changePassword(scanner);
                    break;
                case 5:
                    System.out.println("Exiting the application...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void login(Scanner scanner) {
        System.out.println("Enter Username:");
        String username = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();
        System.out.println("Enter Role (gym customer / gym owner / gym admin):");
        String role = scanner.nextLine();

        // Process login based on role
        switch (role.toLowerCase()) {
            case "gym customer":
                customerMenu(scanner);
                break;
            case "gym owner":
                ownerMenu(scanner);
                break;
            case "gym admin":
                adminMenu(scanner);
                break;
            default:
                System.out.println("Invalid role entered. Returning to main menu.");
        }
    }

    private static void registerCustomer(Scanner scanner) {
        System.out.println("Registering Gym Customer...");
        // Collect customer details for registration (simplified for example)
        System.out.println("Enter Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Age:");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.println("Enter Email:");
        String email = scanner.nextLine();

        // Register the customer (this would call the business logic in the service layer)
        System.out.println("Customer registered successfully!");
    }

    private static void registerGymOwner(Scanner scanner) {
        System.out.println("Registering Gym Owner...");
        // Collect gym owner details for registration
        System.out.println("Enter Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Gym Name:");
        String gymName = scanner.nextLine();
        System.out.println("Enter City:");
        String city = scanner.nextLine();
        System.out.println("Enter Email:");
        String email = scanner.nextLine();

        // Register the gym owner (this would call the business logic in the service layer)
        System.out.println("Gym Owner registered successfully!");
    }

    private static void changePassword(Scanner scanner) {
        System.out.println("Enter your Username:");
        String username = scanner.nextLine();
        System.out.println("Enter current password:");
        String currentPassword = scanner.nextLine();
        System.out.println("Enter new password:");
        String newPassword = scanner.nextLine();

        // Change password logic (simplified for example)
        System.out.println("Password changed successfully!");
    }

    // Gym customer menu
    private static void customerMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n-- Gym Customer Menu --");
            System.out.println("1. View Available Gyms");
            System.out.println("2. Book Slot");
            System.out.println("3. View My Bookings");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Logout");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Viewing available gyms...");
                    break;
                case 2:
                    System.out.println("Booking a slot...");
                    break;
                case 3:
                    System.out.println("Viewing my bookings...");
                    break;
                case 4:
                    System.out.println("Cancelling a booking...");
                    break;
                case 5:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    // Gym owner menu
    private static void ownerMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n-- Gym Owner Menu --");
            System.out.println("1. View My Gyms");
            System.out.println("2. Add Gym Slot");
            System.out.println("3. Edit Gym Details");
            System.out.println("4. View Bookings for My Gym");
            System.out.println("5. Logout");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Viewing my gyms...");
                    break;
                case 2:
                    System.out.println("Adding a gym slot...");
                    break;
                case 3:
                    System.out.println("Editing gym details...");
                    break;
                case 4:
                    System.out.println("Viewing bookings for my gym...");
                    break;
                case 5:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    // Gym admin menu
    private static void adminMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n-- Gym Admin Menu --");
            System.out.println("1. View All Registered Gyms");
            System.out.println("2. View All Users");
            System.out.println("3. Approve/Reject Gym Owner Registration");
            System.out.println("4. View All Bookings");
            System.out.println("5. Logout");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Viewing all registered gyms...");
                    break;
                case 2:
                    System.out.println("Viewing all users...");
                    break;
                case 3:
                    System.out.println("Approving/rejecting gym owner registration...");
                    break;
                case 4:
                    System.out.println("Viewing all bookings...");
                    break;
                case 5:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
}
