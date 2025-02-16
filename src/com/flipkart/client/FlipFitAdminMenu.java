package com.flipkart.client;

import java.util.*;

public class FlipFitAdminMenu {
    private final List<Gym> gyms = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private final List<PendingApproval> pendingApprovals = new ArrayList<>();
    private final List<Booking> bookings = new ArrayList<>();

    public FlipFitAdminMenu() {
        initializeData();
    }

    private void initializeData() {
        gyms.addAll(Arrays.asList(
                new Gym(1, "FitLife Pro", "Delhi Central", "Premium", 4.5, true),
                new Gym(2, "PowerHouse Elite", "Mumbai South", "Elite", 4.8, true),
                new Gym(3, "CrossFit Zone", "Bangalore East", "Standard", 4.2, true)
        ));

        users.addAll(Arrays.asList(
                new User(1, "John Smith", "john@email.com", "Customer", "Active"),
                new User(2, "Sarah Wilson", "sarah@email.com", "Gym Owner", "Active"),
                new User(3, "Mike Johnson", "mike@email.com", "Customer", "Inactive")
        ));

        pendingApprovals.addAll(Arrays.asList(
                new PendingApproval("Alex Brown", "alex@email.com", "FitZone Plus"),
                new PendingApproval("Emma Davis", "emma@email.com", "Elite Fitness")
        ));

        // Initialize Bookings
        bookings.addAll(Arrays.asList(
                new Booking(1, "FitLife Pro", "John Smith", "10:00 AM", "2024-01-20", "Confirmed"),
                new Booking(2, "PowerHouse Elite", "Mike Johnson", "2:00 PM", "2024-01-20", "Pending"),
                new Booking(3, "CrossFit Zone", "Sarah Wilson", "4:00 PM", "2024-01-21", "Confirmed")
        ));
    }

    private void viewAllGyms() {
        System.out.println("\n=== Registered Gyms ===");
        System.out.println("─".repeat(85));
        System.out.printf("%-5s │ %-20s │ %-15s │ %-10s │ %-8s │ %-10s%n",
                "ID", "Name", "Location", "Category", "Rating", "Status");
        System.out.println("─".repeat(85));

        for (Gym gym : gyms) {
            System.out.printf("%-5d │ %-20s │ %-15s │ %-10s │ %-8.1f │ %-10s%n",
                    gym.id, gym.name, gym.location, gym.category,
                    gym.rating, gym.active ? "Active" : "Inactive");
        }
        System.out.println("─".repeat(85));
    }

    private void viewAllUsers() {
        System.out.println("\n=== Registered Users ===");
        System.out.println("─".repeat(75));
        System.out.printf("%-5s │ %-20s │ %-25s │ %-10s │ %-10s%n",
                "ID", "Name", "Email", "Role", "Status");
        System.out.println("─".repeat(75));

        for (User user : users) {
            System.out.printf("%-5d │ %-20s │ %-25s │ %-10s │ %-10s%n",
                    user.id, user.name, user.email, user.role, user.status);
        }
        System.out.println("─".repeat(75));
    }

    private void viewPendingApprovals() {
        System.out.println("\n=== Pending Approvals ===");
        System.out.println("─".repeat(65));
        System.out.printf("%-20s │ %-25s │ %-15s%n",
                "Owner Name", "Email", "Gym Name");
        System.out.println("─".repeat(65));

        for (PendingApproval approval : pendingApprovals) {
            System.out.printf("%-20s │ %-25s │ %-15s%n",
                    approval.ownerName, approval.email, approval.gymName);
        }
        System.out.println("─".repeat(65));
    }

    private void viewAllBookings() {
        System.out.println("\n=== All Bookings ===");
        System.out.println("─".repeat(90));
        System.out.printf("%-5s │ %-20s │ %-15s │ %-10s │ %-12s │ %-10s%n",
                "ID", "Gym", "Customer", "Time", "Date", "Status");
        System.out.println("─".repeat(90));

        for (Booking booking : bookings) {
            System.out.printf("%-5d │ %-20s │ %-15s │ %-10s │ %-12s │ %-10s%n",
                    booking.id, booking.gymName, booking.customerName,
                    booking.time, booking.date, booking.status);
        }
        System.out.println("─".repeat(90));
    }

    // Data model classes
    private static class Gym {
        int id;
        String name;
        String location;
        String category;
        double rating;
        boolean active;

        Gym(int id, String name, String location, String category, double rating, boolean active) {
            this.id = id;
            this.name = name;
            this.location = location;
            this.category = category;
            this.rating = rating;
            this.active = active;
        }
    }

    private static class User {
        int id;
        String name;
        String email;
        String role;
        String status;

        User(int id, String name, String email, String role, String status) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.role = role;
            this.status = status;
        }
    }

    private static class PendingApproval {
        String ownerName;
        String email;
        String gymName;

        PendingApproval(String ownerName, String email, String gymName) {
            this.ownerName = ownerName;
            this.email = email;
            this.gymName = gymName;
        }
    }

    private static class Booking {
        int id;
        String gymName;
        String customerName;
        String time;
        String date;
        String status;

        Booking(int id, String gymName, String customerName, String time, String date, String status) {
            this.id = id;
            this.gymName = gymName;
            this.customerName = customerName;
            this.time = time;
            this.date = date;
            this.status = status;
        }
    }


    public void showMenu(Scanner scanner) {
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

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

    private void displayMenu() {
        System.out.println("\n-- Gym Admin Menu --");
        System.out.println("1. View All Registered Gyms");
        System.out.println("2. View All Users");
        System.out.println("3. View Pending Approvals");
        System.out.println("4. Approve/Reject Gym Owner Registration");
        System.out.println("5. View All Bookings");
        System.out.println("6. Deactivate Gym");
        System.out.println("7. Logout");
    }

    private void approveRejectGymOwner(Scanner scanner) {
        viewPendingApprovals();
        System.out.println("\nEnter Gym Owner's Name:");
        String gymOwnerName = scanner.nextLine();

        // Find the pending approval
        Optional<PendingApproval> pendingApproval = pendingApprovals.stream()
                .filter(approval -> approval.ownerName.equalsIgnoreCase(gymOwnerName))
                .findFirst();

        if (pendingApproval.isEmpty()) {
            System.out.println("No pending approval found for " + gymOwnerName);
            return;
        }

        System.out.println("Enter decision (approve/reject):");
        String decision = scanner.nextLine().toLowerCase();

        if ("approve".equals(decision)) {
            int newId = users.stream().mapToInt(user -> user.id).max().orElse(0) + 1;
            users.add(new User(newId, gymOwnerName, pendingApproval.get().email, "Gym Owner", "Active"));
            pendingApprovals.remove(pendingApproval.get());
            System.out.println("Gym Owner " + gymOwnerName + " approved successfully!");
        } else if ("reject".equals(decision)) {
            pendingApprovals.remove(pendingApproval.get());
            System.out.println("Gym Owner " + gymOwnerName + " rejected.");
        } else {
            System.out.println("Invalid decision. Please enter 'approve' or 'reject'");
        }
    }

    private void deactivateGym(Scanner scanner) {
        viewAllGyms();
        System.out.println("\nEnter Gym ID to deactivate:");
        int gymId = scanner.nextInt();
        scanner.nextLine();

        Optional<Gym> gymToDeactivate = gyms.stream()
                .filter(gym -> gym.id == gymId)
                .findFirst();

        if (gymToDeactivate.isPresent()) {
            Gym gym = gymToDeactivate.get();
            gym.active = false;
            System.out.println("Gym '" + gym.name + "' has been deactivated successfully!");
            viewAllGyms();
        } else {
            System.out.println("No gym found with ID: " + gymId);
        }
    }
}
