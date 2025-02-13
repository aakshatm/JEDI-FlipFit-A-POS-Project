package com.flipkart.client;

import com.flipkart.business.FlipfitAdminService;
import com.flipkart.business.FlipfitGymCustomerService;
import com.flipkart.business.FlipfitGymOwnerService;

import java.util.Scanner;

public class FlipfitApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        FlipfitAdminService adminService = new FlipfitAdminService();
        FlipfitGymCustomerService customerService = new FlipfitGymCustomerService();
        FlipfitGymOwnerService ownerService = new FlipfitGymOwnerService();

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

    private static void findGymBasedonLocation(Scanner scanner){
        System.out.println("Enter city/location: ");
        String cityInput;
        cityInput = scanner.nextLine();
        System.out.println("Searching gym in city " + cityInput);
    }
    private static void bookGymSlot(Scanner scanner){
        System.out.println("Enter gymID: ");
        int gymId;
        gymId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter slot Id: ");
        int slotId;
        slotId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Redirecting to payment.. ");
        paymentHandler(scanner);
    }

    private static void paymentHandler(Scanner scanner){
        System.out.println("Choose mode of Payment: ");
        System.out.println("1. UPI");
        System.out.println("2. Credit/Debit Card");
        System.out.println("3. Net Banking");
        System.out.println("4. Go Back");

        int choice;
        choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (choice) {
            case 1:
                System.out.println("Using UPI for payment");
                break;
            case 2:
                System.out.println("Using Credit/Debit Card for payment");
                break;
            case 3:
                System.out.println("Using Net Banking for payment");
                break;
            case 4:
                System.out.println("going back.. ");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void cancelBookingHandler(Scanner scanner){
        System.out.println("Enter gymID: ");
        int gymId;
        gymId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter slot Id: ");
        int slotId;
        slotId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Booking for the gym id: " + gymId + " and slot id : " + slotId + " is cancelled..");
    }



    // Gym customer menu
    private static void customerMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n-- Gym Customer Menu --");
//            System.out.println("1. View Available Gyms");
//            System.out.println("2. Book Slot");
//            System.out.println("3. View My Bookings");
//            System.out.println("4. Cancel Booking");
//            System.out.println("5. Logout");

            System.out.println("1. View Profile");
            System.out.println("2. Edit Profile");
            System.out.println("3. Find gyms based on location");
            System.out.println("4. Book Gym Slot");
            System.out.println("5. View all Bookings");
            System.out.println("6. Cancel Bookings");
            System.out.println("7. Log Out");



            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Viewing Profile...");
                    break;
                case 2:
                    System.out.println("Editing profile...");
                    break;
                case 3:
                    findGymBasedonLocation(scanner);
                    break;
                case 4:
                    bookGymSlot(scanner);
                    break;
                case 5:
                    System.out.println("viewing all bookings");
                    break;
                case 6:
                    cancelBookingHandler(scanner);
                    break;
                case 7:
                    System.out.println("Logging out.. ");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);
    }

    private static void slotInputHandler(Scanner scanner){
        System.out.println("Enter the number of slots: ");
        int numberOfSlots = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfSlots; i++) {
            int startTime, endTime;
            System.out.println("Enter the starting Time of " + (i + 1)+ " th slot");
            startTime = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the ending Time of " + (i + 1) + " th slot");
            endTime = scanner.nextInt();
            scanner.nextLine();
            if (i != numberOfSlots - 1){
                System.out.println("Enter Details of Next Slot.. ");
            }
        }
    }

    private static void registerNewGym(Scanner scanner){
        System.out.print("Enter Gym Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Gym Location: ");
        String location = scanner.nextLine();

        System.out.print("Enter Gym Capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter slots information");
        slotInputHandler(scanner);

        System.out.println("Gym registered successfully with id: 101");


    }

    private static void gymEditMenu(Scanner scanner){
        System.out.println("1. Edit gym name: ");
        System.out.println("2. Edit gym slots: ");
        System.out.println("3. Edit price: ");
        System.out.println("4. go back ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Enter new gym name: ");
                String newName = scanner.nextLine();
                System.out.println("Edited name to " + newName);
                break;
            case 2:
                slotInputHandler(scanner);
                System.out.println("Slot updated successfully");
                break;
            case 3:
                System.out.println("Enter new price: ");
                int price = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Price updated to " + price);
                break;
            case 4:
                System.out.println("going back..");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }

    }


    private static void editGymDetails(Scanner scanner){
        System.out.print("Enter Gym Id: ");
        int gymId = scanner.nextInt();
        scanner.nextLine();
        gymEditMenu(scanner);
        System.out.println("Successfully edited details");
    }


    // Gym owner menu
    private static void ownerMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n-- Gym Owner Menu --");
//            System.out.println("1. View My Gyms");
//            System.out.println("2. Add Gym Slot");
//            System.out.println("3. Edit Gym Details");
//            System.out.println("4. View Bookings for My Gym");
//            System.out.println("5. Logout");


            System.out.println("1. View Profile");
            System.out.println("2. Edit Profile");
            System.out.println("3. Register his/her gym");
            System.out.println("4. View approved gyms");
            System.out.println("5. View pending approvals");
            System.out.println("6. Edit Gym Details");
            System.out.println("7. Remove his Gym Centers");
            System.out.println("8. Log out");


            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Viewing Profile...");
                    break;
                case 2:
                    System.out.println("Editing Profile...");
                    break;
                case 3:
                    registerNewGym(scanner);
                    break;
                case 4:
                    System.out.println("Viewing approved gyms");
                    break;

                case 5:
                    System.out.println("Viewing pending approvals");
                    break;
                case 6:
                    editGymDetails(scanner);
                    break;
                case 7:
                    System.out.println("Enter gym id: ");
                    int gymId = scanner.nextInt();
                    System.out.println("Removed gym with id " + gymId);
                    break;
                case 8:
                    System.out.println("Logging out..");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);
    }

    // Gym admin menu
    private static void adminMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n-- Gym Admin Menu --");
            System.out.println("1. View All Registered Gyms");
            System.out.println("2. View All Users");
            System.out.println("3. View pending approvals");
            System.out.println("4. Approve/Reject Gym Owner Registration");
            System.out.println("5. View All Bookings");
            System.out.println("6. Deactivate Gym");
            System.out.println("7. Logout");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    viewAllGyms();
                    break;
                case 2:
                    viewAllUsers();
                    break;
                case 3:
                    System.out.println("Viewing all pending approvals..");
                    break;
                case 4:
                    approveRejectGymOwner(scanner);
                    break;
                case 5:
                    viewAllBookings();
                    break;
                case 6:
                    deactivateGym(scanner);
                    break;
                case 7:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);
    }

    private static void viewAllGyms() {
        System.out.println("Displaying all registered gyms...");
        System.out.println("Gym 1: FitLife Gym - Location: City A");
        System.out.println("Gym 2: PowerHouse Gym - Location: City B");
    }

    private static void viewAllUsers() {
        System.out.println("Displaying all users...");
        System.out.println("User 1: John Doe (Customer)");
        System.out.println("User 2: Jane Smith (Gym Owner)");
    }

    private static void approveRejectGymOwner(Scanner scanner) {
        System.out.println("Enter Gym Owner's Name:");
        String gymOwnerName = scanner.nextLine();
        System.out.println("Approve or Reject (Enter 'approve' or 'reject'):");
        String decision = scanner.nextLine();

        if ("approve".equalsIgnoreCase(decision)) {
            System.out.println("Gym Owner " + gymOwnerName + " approved.");
        } else if ("reject".equalsIgnoreCase(decision)) {
            System.out.println("Gym Owner " + gymOwnerName + " rejected.");
        } else {
            System.out.println("Invalid decision.");
        }
    }

    private static void viewAllBookings() {
        System.out.println("Displaying all bookings...");
        System.out.println("Booking 1: Gym 1 - Slot 10:00 AM (Customer: John)");
        System.out.println("Booking 2: Gym 2 - Slot 11:00 AM (Customer: Jane)");
    }

    private static void deactivateGym(Scanner scanner) {
        System.out.println("Enter Gym Name to deactivate:");
        String gymName = scanner.nextLine();
        System.out.println("Confirm deactivation (Yes/No):");
        String confirmation = scanner.nextLine();

        if ("Yes".equalsIgnoreCase(confirmation)) {
            System.out.println("Gym " + gymName + " deactivated.");
        } else {
            System.out.println("Gym deactivation cancelled.");
        }
    }
}
