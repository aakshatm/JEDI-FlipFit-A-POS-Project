package com.flipkart.client;

import java.util.*;

public class FlipFitOwnerMenu {
    private final List<Gym> ownerGyms = new ArrayList<>();
    private final List<GymOwner> gymOwners = new ArrayList<>();
    private final List<PendingApproval> pendingApprovals = new ArrayList<>();
    private final Map<Integer, Slot> gymSlots = new HashMap<>();

    public FlipFitOwnerMenu() {
        initializeData();
    }

    private void initializeData() {
        ownerGyms.addAll(Arrays.asList(
                new Gym(1, "FitLife Pro", "Central Delhi", "Premium", 4.5, true),
                new Gym(2, "PowerHouse Elite", "South Mumbai", "Elite", 4.8, true),
                new Gym(3, "CrossFit Zone", "Indiranagar", "Standard", 4.2, true)
        ));

        gymOwners.addAll(Arrays.asList(
                new GymOwner(101, "John Smith", "john@gym.com", "9876543210", "Premium"),
                new GymOwner(102, "Sarah Wilson", "sarah@gym.com", "9876543211", "Elite")
        ));

        pendingApprovals.addAll(Arrays.asList(
                new PendingApproval("Michael Johnson", "michael@gym.com", "Elite Fitness"),
                new PendingApproval("Emily Brown", "emily@gym.com", "Fitness Hub")
        ));

        gymSlots.put(1, new Slot(1, "06:00", "07:00", 20));
        gymSlots.put(2, new Slot(2, "07:00", "08:00", 15));
        gymSlots.put(3, new Slot(3, "08:00", "09:00", 25));
    }

    private void viewProfile() {
        GymOwner currentOwner = gymOwners.get(0);

        System.out.println("\n=== Your Profile ===");
        System.out.println("─".repeat(75));
        System.out.printf("%-5s │ %-20s │ %-25s │ %-15s │ %-10s%n",
                "ID", "Name", "Email", "Phone", "Type");
        System.out.println("─".repeat(75));

        System.out.printf("%-5d │ %-20s │ %-25s │ %-15s │ %-10s%n",
                currentOwner.id,
                currentOwner.name,
                currentOwner.email,
                currentOwner.phone,
                currentOwner.type);

        System.out.println("─".repeat(75));
    }
    private void registerNewGym(Scanner scanner) {
        System.out.println("\n=== Register New Gym ===");
        System.out.print("Enter Gym Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Location: ");
        String location = scanner.nextLine();

        System.out.print("Enter Category (Standard/Premium/Elite): ");
        String category = scanner.nextLine();

        int newId = ownerGyms.size() + 1;
        ownerGyms.add(new Gym(newId, name, location, category, 0.0, true));

        System.out.println("\nEnter Slot Information:");
        addGymSlots(scanner, newId);

        System.out.println("\nGym registered successfully!");
        viewApprovedGyms();
    }

    private void addGymSlots(Scanner scanner, int gymId) {
        System.out.print("Number of slots to add: ");
        int slotCount = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < slotCount; i++) {
            System.out.println("\nSlot " + (i + 1) + ":");
            System.out.print("Start Time (HH:mm): ");
            String startTime = scanner.nextLine();
            System.out.print("End Time (HH:mm): ");
            String endTime = scanner.nextLine();
            System.out.print("Capacity: ");
            int capacity = scanner.nextInt();
            scanner.nextLine();

            int slotId = gymSlots.size() + 1;
            gymSlots.put(slotId, new Slot(slotId, startTime, endTime, capacity));
        }
    }

    private void viewApprovedGyms() {
        System.out.println("\n=== Approved Gyms ===");
        System.out.println("─".repeat(85));
        System.out.printf("%-5s │ %-20s │ %-15s │ %-10s │ %-8s │ %-10s%n",
                "ID", "Name", "Location", "Category", "Rating", "Status");
        System.out.println("─".repeat(85));

        for (Gym gym : ownerGyms) {
            System.out.printf("%-5d │ %-20s │ %-15s │ %-10s │ %-8.1f │ %-10s%n",
                    gym.id, gym.name, gym.location, gym.category,
                    gym.rating, gym.active ? "Active" : "Inactive");
        }
        System.out.println("─".repeat(85));
    }

    private void viewPendingGymApprovals() {
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

    private void editGymDetails(Scanner scanner) {
        viewApprovedGyms();
        System.out.print("\nEnter Gym ID to edit: ");
        int gymId = scanner.nextInt();
        scanner.nextLine();

        Gym gymToEdit = ownerGyms.stream()
                .filter(gym -> gym.id == gymId)
                .findFirst()
                .orElse(null);

        if (gymToEdit != null) {
            System.out.println("\n1. Edit Name");
            System.out.println("2. Edit Location");
            System.out.println("3. Edit Category");
            System.out.println("4. Edit Slots");
            System.out.println("5. Go Back");

            System.out.print("\nSelect option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter new name: ");
                    gymToEdit.name = scanner.nextLine();
                    break;
                case 2:
                    System.out.print("Enter new location: ");
                    gymToEdit.location = scanner.nextLine();
                    break;
                case 3:
                    System.out.print("Enter new category: ");
                    gymToEdit.category = scanner.nextLine();
                    break;
                case 4:
                    addGymSlots(scanner, gymId);
                    break;
            }
            System.out.println("Gym details updated successfully!");
            viewApprovedGyms();
        } else {
            System.out.println("Gym not found!");
        }
    }

    private void removeGym(Scanner scanner) {
        viewApprovedGyms();
        System.out.print("\nEnter Gym ID to remove: ");
        int gymId = scanner.nextInt();
        scanner.nextLine();

        boolean removed = ownerGyms.removeIf(gym -> gym.id == gymId);
        if (removed) {
            System.out.println("Gym removed successfully!");
            viewApprovedGyms();
        } else {
            System.out.println("Gym not found!");
        }
    }

    private void editProfile(Scanner scanner) {
        GymOwner currentOwner = gymOwners.get(0);

        System.out.println("\n=== Edit Profile ===");
        System.out.println("1. Edit Name");
        System.out.println("2. Edit Email");
        System.out.println("3. Edit Phone Number");
        System.out.println("4. Edit Membership Type");
        System.out.println("5. Go Back");

        System.out.print("\nSelect option to edit: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                currentOwner.name = newName;
                break;
            case 2:
                System.out.print("Enter new email: ");
                String newEmail = scanner.nextLine();
                currentOwner.email = newEmail;
                break;
            case 3:
                System.out.print("Enter new phone number: ");
                String newPhone = scanner.nextLine();
                currentOwner.phone = newPhone;
                break;
            case 4:
                System.out.println("Select membership type:");
                System.out.println("1. Standard");
                System.out.println("2. Premium");
                System.out.println("3. Elite");
                System.out.print("Enter choice: ");
                int typeChoice = scanner.nextInt();
                scanner.nextLine();

                switch (typeChoice) {
                    case 1:
                        currentOwner.type = "Standard";
                        break;
                    case 2:
                        currentOwner.type = "Premium";
                        break;
                    case 3:
                        currentOwner.type = "Elite";
                        break;
                    default:
                        System.out.println("Invalid choice. Membership type not changed.");
                        return;
                }
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("\nProfile updated successfully!");
        viewProfile();
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

    private void displayMenu() {
        System.out.println("\n=== Gym Owner Menu ===");
        System.out.println("1. View Profile");
        System.out.println("2. Edit Profile");
        System.out.println("3. Register a new gym");
        System.out.println("4. View approved gyms");
        System.out.println("5. View pending approvals");
        System.out.println("6. Edit Gym Details");
        System.out.println("7. Remove Gym");
        System.out.println("8. Log out");
        System.out.print("\nEnter your choice: ");
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

    private static class GymOwner {
        int id;
        String name;
        String email;
        String phone;
        String type;

        GymOwner(int id, String name, String email, String phone, String type) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.type = type;
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

    private static class Slot {
        int id;
        String startTime;
        String endTime;
        int capacity;

        Slot(int id, String startTime, String endTime, int capacity) {
            this.id = id;
            this.startTime = startTime;
            this.endTime = endTime;
            this.capacity = capacity;
        }
    }
}
