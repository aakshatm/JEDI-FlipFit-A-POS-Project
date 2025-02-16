package com.flipkart.client;

import java.util.*;

public class FlipFitCustomerMenu {
    private final Map<String, CustomerProfile> profiles = new HashMap<>();
    private final List<Booking> bookings = new ArrayList<>();
    private final List<GymInfo> gymDatabase = new ArrayList<>();

    private void initializeGymData() {
        gymDatabase.addAll(Arrays.asList(
                new GymInfo(1, "FitLife Pro", "Central Area", 4.5, 1500, "6AM - 10PM", "Premium"),
                new GymInfo(2, "PowerHouse", "West Zone", 4.8, 2000, "5AM - 11PM", "Elite"),
                new GymInfo(3, "Elite Fitness", "East Zone", 4.2, 1800, "24/7", "Standard"),
                new GymInfo(4, "GoldGym Premium", "North Zone", 4.7, 2500, "5AM - 11PM", "Premium"),
                new GymInfo(5, "FitZone", "South Zone", 4.4, 1700, "6AM - 10PM", "Standard")
        ));
    }
    public FlipFitCustomerMenu() {
        initializeData();
        initializeGymData();
    }

    private void initializeData() {
        // Initialize sample profile
        profiles.put("user1", new CustomerProfile("John Doe", "john@email.com", "9876543210", "Premium"));

        // Initialize sample bookings
        bookings.add(new Booking(1, "FitLife Gym", "10:00 AM", "2024-01-20", "Confirmed"));
        bookings.add(new Booking(2, "PowerHouse Gym", "2:00 PM", "2024-01-21", "Pending"));
        bookings.add(new Booking(3, "Elite Fitness", "4:30 PM", "2024-01-22", "Confirmed"));
    }

    public void showMenu(Scanner scanner) {
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewProfile();
                    break;
                case 2:
                    editProfile(scanner);
                    break;
                case 3:
                    findGymBasedOnLocation(scanner);
                    break;
                case 4:
                    bookGymSlot(scanner);
                    break;
                case 5:
                    viewAllBookings();
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

    private void viewProfile() {
        CustomerProfile profile = profiles.get("user1");
        System.out.println("\n=== Customer Profile ===");
        System.out.printf("%-15s: %s%n", "Name", profile.name);
        System.out.printf("%-15s: %s%n", "Email", profile.email);
        System.out.printf("%-15s: %s%n", "Phone", profile.phone);
        System.out.printf("%-15s: %s%n", "Membership", profile.membershipType);
    }

    private void editProfile(Scanner scanner) {
        CustomerProfile profile = profiles.get("user1");
        System.out.println("\n=== Edit Profile ===");
        System.out.print("Enter new name (current: " + profile.name + "): ");
        profile.name = scanner.nextLine();
        System.out.print("Enter new email (current: " + profile.email + "): ");
        profile.email = scanner.nextLine();
        System.out.print("Enter new phone (current: " + profile.phone + "): ");
        profile.phone = scanner.nextLine();

        System.out.println("\nProfile updated successfully!");
        viewProfile();
    }

    private void viewAllBookings() {
        System.out.println("\n=== Your Bookings ===");
        System.out.printf("%-5s | %-15s | %-10s | %-12s | %-10s%n",
                "ID", "Gym", "Time", "Date", "Status");
        System.out.println("----------------------------------------------------------");

        for (Booking booking : bookings) {
            System.out.printf("%-5d | %-15s | %-10s | %-12s | %-10s%n",
                    booking.id,
                    booking.gymName,
                    booking.time,
                    booking.date,
                    booking.status);
        }
    }

    private static class CustomerProfile {
        String name;
        String email;
        String phone;
        String membershipType;

        CustomerProfile(String name, String email, String phone, String membershipType) {
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.membershipType = membershipType;
        }
    }

    private static class Booking {
        int id;
        String gymName;
        String time;
        String date;
        String status;

        Booking(int id, String gymName, String time, String date, String status) {
            this.id = id;
            this.gymName = gymName;
            this.time = time;
            this.date = date;
            this.status = status;
        }
    }

    private void displayMenu() {
        System.out.println("\n-- Gym Customer Menu --");
        System.out.println("1. View Profile");
        System.out.println("2. Edit Profile");
        System.out.println("3. Find gyms based on location");
        System.out.println("4. Book Gym Slot");
        System.out.println("5. View all Bookings");
        System.out.println("6. Cancel Bookings");
        System.out.println("7. Log Out");
    }

    private void findGymBasedOnLocation(Scanner scanner) {
        System.out.println("\nEnter city: ");
        String cityInput = scanner.nextLine();
        displayGyms(cityInput);
    }

    private void displayGyms(String city) {
        System.out.println("\n=== Available Gyms in " + city.toUpperCase() + " ===");
        System.out.println("─".repeat(95));
        System.out.printf("%-4s │ %-20s │ %-15s │ %-7s │ %-8s │ %-12s │ %-10s%n",
                "ID", "Name", "Location", "Rating", "Price(₹)", "Timings", "Category");
        System.out.println("─".repeat(95));

        for (GymInfo gym : gymDatabase) {
            System.out.printf("%-4d │ %-20s │ %-15s │ %-7.1f │ %-8d │ %-12s │ %-10s%n",
                    gym.id,
                    gym.name,
                    gym.location,
                    gym.rating,
                    gym.price,
                    gym.timings,
                    gym.category);
        }
        System.out.println("─".repeat(95));
    }

    private static class GymInfo {
        int id;
        String name;
        String location;
        double rating;
        int price;
        String timings;
        String category;

        GymInfo(int id, String name, String location, double rating, int price, String timings, String category) {
            this.id = id;
            this.name = name;
            this.location = location;
            this.rating = rating;
            this.price = price;
            this.timings = timings;
            this.category = category;
        }
    }

    private void bookGymSlot(Scanner scanner) {
        System.out.println("Enter gymID: ");
        int gymId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter slot Id: ");
        int slotId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Redirecting to payment.. ");
        handlePayment(scanner);
    }

    private void handlePayment(Scanner scanner) {
        System.out.println("Choose mode of Payment: ");
        System.out.println("1. UPI");
        System.out.println("2. Credit/Debit Card");
        System.out.println("3. Net Banking");
        System.out.println("4. Go Back");

        int choice = scanner.nextInt();
        scanner.nextLine();

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

    private void cancelBookingHandler(Scanner scanner) {
        System.out.println("Enter gymID: ");
        int gymId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter slot Id: ");
        int slotId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Booking for the gym id: " + gymId + " and slot id : " + slotId + " is cancelled..");
    }
}
