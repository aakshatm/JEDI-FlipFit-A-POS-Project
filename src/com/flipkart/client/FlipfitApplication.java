package com.flipkart.client;

import java.util.Scanner;

import com.flipkart.business.FlipfitAdminInterface;
import com.flipkart.business.FlipfitGymCustomerInterface;
import com.flipkart.business.FlipfitGymOwnerInterface;
import com.flipkart.business.FlipfitAdminService;
import com.flipkart.business.FlipfitGymCustomerService;
import com.flipkart.business.FlipfitGymOwnerService;

public class FlipfitApplication {
    private static final FlipFitCustomerMenu customerMenu = new FlipFitCustomerMenu();
    private static final FlipFitOwnerMenu ownerMenu = new FlipFitOwnerMenu();
    private static final FlipFitAdminMenu FLIP_FIT_ADMIN_MENU = new FlipFitAdminMenu();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        FlipfitAdminInterface adminService = new FlipfitAdminService();
        FlipfitGymCustomerInterface customerService = new FlipfitGymCustomerService();
        FlipfitGymOwnerInterface ownerService = new FlipfitGymOwnerService();

        System.out.println("Welcome to FlipFit Application");

        do {
            displayMainMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    handleLogin(scanner);
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

    private static void displayMainMenu() {
        System.out.println("\nEnter your choice:");
        System.out.println("1. Login");
        System.out.println("2. Registration of Gym Customer");
        System.out.println("3. Registration of Gym Owner");
        System.out.println("4. Change Password");
        System.out.println("5. Exit");
    }

    private static void handleLogin(Scanner scanner) {
        System.out.println("Enter Username:");
        String username = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();
        System.out.println("Enter Role (gym customer / gym owner / gym admin):");
        String role = scanner.nextLine();

        switch (role.toLowerCase()) {
            case "gym customer":
                customerMenu.showMenu(scanner);
                break;
            case "gym owner":
                ownerMenu.showMenu(scanner);
                break;
            case "gym admin":
                FLIP_FIT_ADMIN_MENU.showMenu(scanner);
                break;
            default:
                System.out.println("Invalid role entered.");
        }
    }

    private static void registerCustomer(Scanner scanner) {
        System.out.println("Registering Gym Customer...");
        System.out.println("Enter Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Age:");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Email:");
        String email = scanner.nextLine();
        System.out.println("Enter Username:");
        String username = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        System.out.println("Customer registered successfully!");
    }

    private static void registerGymOwner(Scanner scanner) {
        System.out.println("Registering Gym Owner...");
        System.out.println("Enter Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Gym Name:");
        String gymName = scanner.nextLine();
        System.out.println("Enter City:");
        String city = scanner.nextLine();
        System.out.println("Enter Email:");
        String email = scanner.nextLine();
        System.out.println("Enter Username:");
        String username = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        System.out.println("Gym Owner registered successfully! Awaiting admin approval.");
    }

    private static void changePassword(Scanner scanner) {
        System.out.println("Enter your Username:");
        String username = scanner.nextLine();
        System.out.println("Enter current password:");
        String currentPassword = scanner.nextLine();
        System.out.println("Enter new password:");
        String newPassword = scanner.nextLine();
        System.out.println("Confirm new password:");
        String confirmPassword = scanner.nextLine();

        if (newPassword.equals(confirmPassword)) {
            System.out.println("Password changed successfully!");
        } else {
            System.out.println("New passwords do not match. Please try again.");
        }
    }
}
