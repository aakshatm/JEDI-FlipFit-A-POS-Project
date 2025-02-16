package com.flipkart.client;

import com.flipkart.bean.FlipfitGymCenter;
import com.flipkart.bean.Slot;
import com.flipkart.business.FlipfitGymOwnerInterface;
import com.flipkart.business.FlipfitGymOwnerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlipFitOwnerMenu {
//    System.out.println("1. View Profile");
//            System.out.println("2. Edit Profile");
//            System.out.println("3. Register his/her gym");
//            System.out.println("4. View approved gyms");
//            System.out.println("5. View pending approvals");
//            System.out.println("6. Edit Gym Details");
//            System.out.println("7. Remove his Gym Centers");
//            System.out.println("8. Log out");

    private static FlipfitGymOwnerInterface ownerService = new FlipfitGymOwnerService();
    private static Scanner scanner = new Scanner(System.in);
    
    public void addGym(String email){
        FlipfitGymCenter gym = new FlipfitGymCenter();
        int gymOwnerId = ownerService.getGymOwnerIdByEmail(email);
        if (gymOwnerId == -1) {
            System.out.println("No such gym owner exists with email: " + email);
            return;
        }
        gym.setOwnerId(gymOwnerId);

        System.out.println("Enter details of the gym: ");
        System.out.println("Name: " );
        String gymName = scanner.nextLine();
        System.out.println( "Address: ");
        String address = scanner.nextLine();
        
        System.out.println( "Location: ");
        String location = scanner.nextLine();
        
        String gymStatus = "unverified";

        gym.setGymAddress(address);
        gym.setLocation(location);
        gym.setGymName(gymName);
        gym.setStatus(gymStatus);

        List<Slot> slots = new ArrayList<>();
        System.out.println("Please enter number of slots: ");
        int slotCount = Integer.parseInt(scanner.nextLine());
        
        int currentCount = 1;
        while (currentCount <= slotCount) {
            System.out.println("Add details for slot number " + currentCount + ": ");
            System.out.println("Enter start time: ");
            int startTime = Integer.parseInt(scanner.nextLine());
            
            System.out.println("Enter available seats: ");
            int seatCount = Integer.parseInt(scanner.nextLine());
            
            Slot slot = new Slot(-1, startTime, seatCount);
            slots.add(slot);
            currentCount++;
        }

        gym.setSlots(slots);
        if (ownerService.addGym(gym))
            System.out.println("Gym added successfully!" );
        else
            System.out.println("Gym could not be added!" );
    }

    private void addSlots() {
        System.out.println( "Gym ID: " );
        int gymId = Integer.parseInt(scanner.nextLine());

        List<Slot> slots = new ArrayList<>();
        System.out.println("Please enter number of slots: " );
        int slotCount = Integer.parseInt(scanner.nextLine());

        int currentCount = 1;
        while (currentCount <= slotCount) {
            System.out.println("Add details for slot number " + currentCount + ": ");
            System.out.println("Enter start time: ");
            int startTime = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter available seats: ");
            int seatCount = Integer.parseInt(scanner.nextLine());

            Slot slot = new Slot(-1, startTime, seatCount);
            slots.add(slot);
            currentCount++;
        }
        if(ownerService.addSlots(gymId, slots))
            System.out.println( "Gym added successfully!" );
        else
            System.out.println( "Gym could not be added!" );
    }

    /// Displays all gyms owned by the logged-in gym owner.
    /// @param email Gym owner's email address.
    private void displayGyms(String email) {
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

        for (FlipfitGymCenter gym : gymsList) {
            System.out.format("+-------+----------------------+------------------------------------------+----------------------+\n");
            System.out.format("| Gym ID|     Name             |           Address                        |     Location         |\n");
            System.out.format("+-------+----------------------+------------------------------------------+----------------------+\n");
            System.out.format(gymLeftAlignFormat, gym.getGymId(), gym.getGymName(), gym.getGymAddress(), gym.getLocation());
            System.out.format("+-------+----------------------+------------------------------------------+----------------------+\n");
            System.out.println("Slots: ");
            System.out.format("+-------+---------------+-------+\n");
            System.out.format("|Slot ID|     Time      | Seats |\n");
            System.out.format("+-------+---------------+-------+\n");

            for (Slot slot : gym.getSlots()) {
                System.out.format(slotLeftAlignFormat, slot.getSlotsId(), slot.getStartTime() + " - " + (slot.getStartTime() + 1), slot.getSeatCount());
            }
            System.out.format("+-------+---------------+-------+\n");
            System.out.println();
        }
    }


    /**
     * Updates the seat count for a specific gym and slot.
     */
    private void updateSeatCount(String email) {
        displayGyms(email);
        System.out.println("Enter gym ID: ");
        int gymId = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter start time: ");
        int startTime = Integer.parseInt(scanner.nextLine());

        System.out.println("Update seat count by: ");
        int seatCount = Integer.parseInt(scanner.nextLine());

        if (ownerService.updateSeatCount(gymId, startTime, seatCount))
            System.out.println("Seat count updated!");
        else
            System.out.println("Seat count not updated");
    }

    /**
     * Updates the details of an existing gym.
     * @param email email of the gymOwner
     * @return true if gym details are updated successfully, false otherwise.
     * */
    public boolean updateGym(String email) {
        displayGyms(email);

        System.out.println("Enter gym ID: ");
        int gymId = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter gym details: ");
        System.out.println("Name: ");
        String gymName = scanner.nextLine();
        System.out.println("Gym Address: ");
        String gymAddress = scanner.nextLine();

        System.out.println("Gym Location: ");
        String gymALocation = scanner.nextLine();


        FlipfitGymCenter gym = new FlipfitGymCenter();
        gym.setGymName(gymName);
        gym.setGymAddress(gymAddress);
        gym.setLocation(gymALocation);
        gym.setGymId(gymId);

        return ownerService.updateGymDetails(gym);
    }
    
}

