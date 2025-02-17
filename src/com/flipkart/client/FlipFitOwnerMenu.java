package com.flipkart.client;

import com.flipkart.bean.FlipfitGymCenter;
import com.flipkart.bean.FlipfitGymOwner;
import com.flipkart.bean.Slot;
import com.flipkart.business.FlipfitGymOwnerInterface;
import com.flipkart.business.FlipfitGymOwnerService;

import java.util.*;
import java.util.stream.IntStream;

public class FlipFitOwnerMenu {

    private static final FlipfitGymOwnerInterface ownerService = new FlipfitGymOwnerService();
    private static final Scanner scanner = new Scanner(System.in);

    public void viewProfile(String email, String password) {
        FlipfitGymOwner owner = ownerService.getProfile(email, password);
        owner.display();
    }

    boolean verifyGymOwner(String email, String password) {
        return ownerService.validateGymOwner(email, password);
    }

    public boolean gymOwnerLogin(String email, String password) {
        if (!verifyGymOwner(email, password)) {
            return false;
        }
        System.out.println("Login Successful! (Gym Owner)");
        while (true) {
            System.out.println("-----------------Gym Owner Menu-----------------");
            System.out.println("Press 1 to add a gym");
            System.out.println("Press 2 to update gym details");
            System.out.println("Press 3 to view all gyms");
            System.out.println("Press 4 to add slots");
            System.out.println("Press 5 to update seat count by");
            System.out.println("Press 6 to update your details");
            System.out.println("Press 7 to view profile");
            System.out.println("Press 8 to logout");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addGym(email);
                    break;
                case 2:
                    updateGym(email);
                    break;
                case 3:
                    displayGyms(email);
                    break;
                case 4:
                    addSlots();
                    break;
                case 5:
                    updateSeatCount(email);
                    break;
                case 6:
                    System.out.println(updateGymOwnerDetails() ? "Gym owner updated successfully!" : "Gym owner not updated");
                    break;
                case 7:
                    viewProfile(email, password);
                    break;
                case 8:
                    return true;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    public void addGym(String email) {
        int gymOwnerId = ownerService.getGymOwnerIdByEmail(email);
        if (gymOwnerId == -1) {
            System.out.println("No such gym owner exists with email: " + email);
            return;
        }

        FlipfitGymCenter gym = new FlipfitGymCenter();
        gym.setOwnerId(gymOwnerId);

        System.out.println("Enter details of the gym: ");
        gym.setGymName(scanner.nextLine());
        gym.setGymAddress(scanner.nextLine());
        gym.setLocation(scanner.nextLine());
        gym.setStatus("unverified");

        List<Slot> slots = new ArrayList<>();
        System.out.println("Please enter number of slots: ");
        int slotCount = Integer.parseInt(scanner.nextLine());

        IntStream.range(1, slotCount + 1).forEach(currentCount -> {
            System.out.println("Add details for slot number " + currentCount + ": ");
            int startTime = Integer.parseInt(scanner.nextLine());
            int seatCount = Integer.parseInt(scanner.nextLine());
            slots.add(new Slot(-1, startTime, seatCount));
        });

        gym.setSlots(slots);
        System.out.println(ownerService.addGym(gym) ? "Gym added successfully!" : "Gym could not be added!");
    }

    public void addSlots() {
        System.out.println("Gym ID: ");
        int gymId = Integer.parseInt(scanner.nextLine());

        List<Slot> slots = new ArrayList<>();
        System.out.println("Please enter number of slots: ");
        int slotCount = Integer.parseInt(scanner.nextLine());

        IntStream.range(1, slotCount + 1).forEach(currentCount -> {
            System.out.println("Add details for slot number " + currentCount + ": ");
            int startTime = Integer.parseInt(scanner.nextLine());
            int seatCount = Integer.parseInt(scanner.nextLine());
            slots.add(new Slot(-1, startTime, seatCount));
        });

        System.out.println(ownerService.addSlots(gymId, slots) ? "Gym added successfully!" : "Gym could not be added!");
    }

    public void displayGyms(String email) {
        int gymOwnerId = ownerService.getGymOwnerIdByEmail(email);
        if (gymOwnerId == -1) {
            System.out.println("No such gym owner exists with email: " + email);
            return;
        }

        List<FlipfitGymCenter> gymsList = ownerService.viewMyGyms(gymOwnerId);
        if (gymsList.isEmpty()) {
            System.out.println("No gyms found for the gym owner with email: " + email);
            return;
        }

        String gymLeftAlignFormat = "| %-5d | %-20s | %-40s | %-20s |%n";
        String slotLeftAlignFormat = "| %-5d | %-15s | %-5d |%n";

        gymsList.forEach(gym -> {
            System.out.format("+-------+----------------------+------------------------------------------+----------------------+\n");
            System.out.format("| Gym ID|     Name             |           Address                        |     Location         |\n");
            System.out.format("+-------+----------------------+------------------------------------------+----------------------+\n");
            System.out.format(gymLeftAlignFormat, gym.getGymId(), gym.getGymName(), gym.getGymAddress(), gym.getLocation());
            System.out.format("+-------+----------------------+------------------------------------------+----------------------+\n");

            System.out.println("Slots: ");
            System.out.format("+-------+---------------+-------+\n");
            System.out.format("|Slot ID|     Time      | Seats |\n");
            System.out.format("+-------+---------------+-------+\n");

            gym.getSlots().forEach(slot ->
                    System.out.format(slotLeftAlignFormat, slot.getSlotsId(), slot.getStartTime() + " - " + (slot.getStartTime() + 1), slot.getSeatCount())
            );
            System.out.format("+-------+---------------+-------+\n\n");
        });
    }

    public boolean updatePassword(String userMail, String password, String updatedPassword) {
        return ownerService.updateGymOwnerPassword(userMail, password, updatedPassword);
    }

    public void updateSeatCount(String email) {
        displayGyms(email);
        System.out.println("Enter gym ID: ");
        int gymId = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter start time: ");
        int startTime = Integer.parseInt(scanner.nextLine());

        System.out.println("Update seat count by: ");
        int seatCount = Integer.parseInt(scanner.nextLine());

        System.out.println(ownerService.updateSeatCount(gymId, startTime, seatCount) ? "Seat count updated!" : "Seat count not updated");
    }

    public void updateGym(String email) {
        displayGyms(email);

        System.out.println("Enter gym ID: ");
        int gymId = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter gym details: ");
        System.out.println("Name: ");
        String gymName = scanner.nextLine();
        System.out.println("Gym Address: ");
        String gymAddress = scanner.nextLine();
        System.out.println("Gym Location: ");
        String gymLocation = scanner.nextLine();

        FlipfitGymCenter gym = new FlipfitGymCenter();
        gym.setGymName(gymName);
        gym.setGymAddress(gymAddress);
        gym.setLocation(gymLocation);
        gym.setGymId(gymId);

        ownerService.updateGymDetails(gym);
    }

    private boolean updateGymOwnerDetails() {
        System.out.println("Enter gym owner details:");
        System.out.println("Email: ");
        String ownerEmail = scanner.nextLine();

        System.out.println("Name: ");
        String ownerName = scanner.nextLine();
        System.out.println("Phone Number: ");
        String phoneNo = scanner.nextLine();

        FlipfitGymOwner gymOwner = new FlipfitGymOwner();
        gymOwner.setOwnerEmail(ownerEmail);
        gymOwner.setOwnerName(ownerName);
        gymOwner.setPhoneNo(phoneNo);

        return ownerService.updateGymOwner(gymOwner);
    }

    public void createGymOwner() {
        System.out.println("Enter gym owner details:");
        System.out.println("Email: ");
        String ownerEmail = scanner.nextLine();

        System.out.println("Name: ");
        String ownerName = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();

        System.out.println("Phone Number: ");
        String phoneNo = scanner.nextLine();

        System.out.println("National ID: ");
        String nationalId = scanner.nextLine();

        if (nationalId.length() != 12) {
            System.out.println("Invalid national ID! Length must be 12");
            return;
        }

        System.out.println("GST: ");
        String GST = scanner.nextLine();

        System.out.println("PAN Number: ");
        String PAN = scanner.nextLine();

        if (PAN.length() != 10) {
            System.out.println("Invalid PAN Card Number. Length must be 10");
            return;
        }

        FlipfitGymOwner gymOwner = new FlipfitGymOwner();
        gymOwner.setOwnerEmail(ownerEmail);
        gymOwner.setPAN(PAN);
        gymOwner.setOwnerName(ownerName);
        gymOwner.setGST(GST);
        gymOwner.setPassword(password);
        gymOwner.setNationalId(nationalId);
        gymOwner.setPhoneNo(phoneNo);
        gymOwner.setGyms(new ArrayList<>());
        gymOwner.setVerificationStatus("unverified");

        System.out.println(ownerService.createGymOwner(gymOwner) ? "Gym owner created!" : "Gym owner not created!");
    }
}
