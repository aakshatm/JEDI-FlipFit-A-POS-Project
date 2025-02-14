package com.flipkart.client;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

import com.flipkart.business.FlipfitAdminInterface;
import com.flipkart.business.FlipfitGymCustomerInterface;
import com.flipkart.business.FlipfitGymOwnerInterface;

import com.flipkart.business.FlipfitAdminService;
import com.flipkart.business.FlipfitGymCustomerService;
import com.flipkart.business.FlipfitGymOwnerService;

import java.util.Scanner;

public class FlipfitApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        FlipfitAdminInterface adminService = new FlipfitAdminService();
        FlipfitGymCustomerInterface customerService = new FlipfitGymCustomerService();
        FlipfitGymOwnerInterface ownerService = new FlipfitGymOwnerService();

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

    private static void findGymBasedonLocation(Scanner scanner) {
        System.out.println("Enter city/location: ");
        String cityInput;
        cityInput = scanner.nextLine();
        System.out.println("Searching gym in city " + cityInput);
    }

    private static void bookGymSlot(Scanner scanner) {
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

    private static void paymentHandler(Scanner scanner) {
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

    public static void cancelBookingHandler(Scanner scanner) {
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

    private static final List<String> ownerGyms = new ArrayList<>();
    private static final Set<String> gymUsers = new HashSet<>();
    private static final Map<String, String> gymPendingApprovals = new HashMap<>();
    private static final List<String> gymBokings = new ArrayList<>();
    private static final Map<Integer, String> gymOwnerGymSlots = new HashMap<>();

    static {
        ownerGyms.add("FitLife Gym - Location: City A");
        ownerGyms.add("PowerHouse Gym - Location: City B");

        gymUsers.add("John Doe (Customer)");
        gymUsers.add("Jane Smith (Gym Owner)");

        gymPendingApprovals.put("Michael Johnson", "Gym Owner");
        gymPendingApprovals.put("Emily Davis", "Gym Owner");

        gymBokings.add("Gym 1 - Slot 10:00 AM (Customer: John)");
        gymBokings.add("Gym 2 - Slot 11:00 AM (Customer: Jane)");

        gymOwnerGymSlots.put(101, "10:00 AM - 11:00 AM");
        gymOwnerGymSlots.put(102, "11:00 AM - 12:00 PM");
    }

    private static void slotInputHandler(Scanner scanner) {
        System.out.println("Enter the number of slots: ");
        int numberOfSlots = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfSlots; i++) {
            System.out.println("Enter the starting Time of slot " + (i + 1) + " (in 24-hour format):");
            int startTime = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the ending Time of slot " + (i + 1) + " (in 24-hour format):");
            int endTime = scanner.nextInt();
            scanner.nextLine();
            gymOwnerGymSlots.put(100 + i, startTime + ":00 - " + endTime + ":00");
        }
    }

    private static void registerNewGym(Scanner scanner) {
        System.out.print("Enter Gym Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Gym Location: ");
        String location = scanner.nextLine();

        System.out.println("Enter slots information:");
        slotInputHandler(scanner);


        ownerGyms.add(name + " - Location: " + location);
        System.out.println("Gym registered successfully.");
    }

    private static void viewApprovedGyms() {
        System.out.println("Approved Gyms:");
        if (ownerGyms.isEmpty()) {
            System.out.println("No gyms available.");
        } else {
            for (int i = 0; i < ownerGyms.size(); i++) {
                System.out.println((i + 1) + ". " + ownerGyms.get(i));
            }
        }
    }

    private static void viewPendingGymApprovals() {
        System.out.println("Pending Approvals:");
        if (gymPendingApprovals.isEmpty()) {
            System.out.println("No pending approvals.");
        } else {
            gymPendingApprovals.forEach((name, role) -> System.out.println(name + " - " + role));
        }
    }

    private static void editGymDetails(Scanner scanner) {
        viewApprovedGyms();
        System.out.print("Enter Gym number to edit: ");
        int gymIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        if (gymIndex >= 0 && gymIndex < ownerGyms.size()) {
            System.out.println("Editing gym: " + ownerGyms.get(gymIndex));
            System.out.println("1. Edit name");
            System.out.println("2. Edit slots");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                ownerGyms.set(gymIndex, newName);
                System.out.println("Gym name updated.");
            } else if (choice == 2) {
                slotInputHandler(scanner);
                System.out.println("Slots updated.");
            }
        } else {
            System.out.println("Invalid gym number.");
        }
    }

    private static void removeGym(Scanner scanner) {
        viewApprovedGyms();
        System.out.print("Enter Gym number to remove: ");
        int gymIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        if (gymIndex >= 0 && gymIndex < ownerGyms.size()) {
            System.out.println("Removed: " + ownerGyms.remove(gymIndex));
        } else {
            System.out.println("Invalid gym number.");
        }
    }

    private static void ownerMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n-- Gym Owner Menu --");
            System.out.println("1. View Profile");
            System.out.println("2. Edit Profile");
            System.out.println("3. Register a new gym");
            System.out.println("4. View approved gyms");
            System.out.println("5. View pending approvals");
            System.out.println("6. Edit Gym Details");
            System.out.println("7. Remove Gym");
            System.out.println("8. Log out");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Gym Owners: " + gymUsers);
                    break;
                case 2:
                    System.out.println("Editing Profile... (Feature coming soon)");
                    break;
                case 3:
                    registerNewGym(scanner);
                    break;
                case 4:
                    viewApprovedGyms();
                    break;
                case 5:
                    viewPendingGymApprovals();
                    break;
                case 6:
                    editGymDetails(scanner);
                    break;
                case 7:
                    removeGym(scanner);
                    break;
                case 8:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 8);
    }

    private static final List<String> gyms = new ArrayList<>();
    private static final Set<String> users = new HashSet<>();
    private static final Map<String, String> pendingApprovals = new HashMap<>();
    private static final List<String> bookings = new ArrayList<>();

    static {
        // Pre-populated Gyms
        gyms.add("FitLife Gym - Location: City A");
        gyms.add("PowerHouse Gym - Location: City B");

        // Pre-populated Users
        users.add("John Doe (Customer)");
        users.add("Jane Smith (Gym Owner)");

        // Pre-populated Pending Approvals
        pendingApprovals.put("Michael Johnson", "Gym Owner");
        pendingApprovals.put("Emily Davis", "Gym Owner");

        // Pre-populated Bookings
        bookings.add("Gym 1 - Slot 10:00 AM (Customer: John)");
        bookings.add("Gym 2 - Slot 11:00 AM (Customer: Jane)");
    }

    private static void adminMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n-- Gym Admin Menu --");
            System.out.println("1. View All Registered Gyms");
            System.out.println("2. View All Users");
            System.out.println("3. View Pending Approvals");
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
                    viewPendingApprovals();
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
        } while (choice != 7);
    }

    private static void viewAllGyms() {
        System.out.println("Displaying all registered gyms...");
        for (String gym : gyms) {
            System.out.println(gym);
        }
    }

    private static void viewAllUsers() {
        System.out.println("Displaying all users...");
        for (String user : users) {
            System.out.println(user);
        }
    }

    private static void viewPendingApprovals() {
        System.out.println("Viewing all pending approvals...");
        if (pendingApprovals.isEmpty()) {
            System.out.println("No pending approvals.");
        } else {
            for (Map.Entry<String, String> entry : pendingApprovals.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
        }
    }

    private static void approveRejectGymOwner(Scanner scanner) {
        System.out.println("Enter Gym Owner's Name:");
        String gymOwnerName = scanner.nextLine();

        if (!pendingApprovals.containsKey(gymOwnerName)) {
            System.out.println("No pending approval found for " + gymOwnerName);
            return;
        }

        System.out.println("Approve or Reject (Enter 'approve' or 'reject'):");
        String decision = scanner.nextLine();

        if ("approve".equalsIgnoreCase(decision)) {
            users.add(gymOwnerName + " (Gym Owner)");
            pendingApprovals.remove(gymOwnerName);
            System.out.println("Gym Owner " + gymOwnerName + " approved.");
        } else if ("reject".equalsIgnoreCase(decision)) {
            pendingApprovals.remove(gymOwnerName);
            System.out.println("Gym Owner " + gymOwnerName + " rejected.");
        } else {
            System.out.println("Invalid decision.");
        }
    }

    private static void viewAllBookings() {
        System.out.println("Displaying all bookings...");
        for (String booking : bookings) {
            System.out.println(booking);
        }
    }

    private static void deactivateGym(Scanner scanner) {
        System.out.println("Enter Gym Name to deactivate:");
        String gymName = scanner.nextLine();

        if (gyms.removeIf(g -> g.contains(gymName))) {
            System.out.println("Gym " + gymName + " deactivated.");
        } else {
            System.out.println("No gym found with the name " + gymName);
        }
    }
}

