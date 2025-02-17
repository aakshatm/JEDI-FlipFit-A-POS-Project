package com.flipkart.client;

import com.flipkart.bean.FlipfitGymCenter;
import com.flipkart.bean.FlipfitGymOwner;
import com.flipkart.bean.Slot;
import com.flipkart.business.FlipfitGymOwnerInterface;
import com.flipkart.business.FlipfitGymOwnerService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public void viewProfile(String email, String password){
        FlipfitGymOwner owner = ownerService.getProfile(email, password);
        owner.display();
    }

    /// Verifies if the provided email and password match a registered gym owner.
    /// @param email Gym owner's email address.
    /// @param password Gym owner's password.
    /// @return true if the credentials are valid, false otherwise.
    boolean verifyGymOwner(String email, String password) {
        return ownerService.validateGymOwner(email, password);
    }

    /// Handles the gym owner login process and displays the menu options.
    /// @param email Gym owner's email address.
    /// @param password Gym owner's password.
    /// @return true if logout is successful, false otherwise.
    public boolean gymOwnerLogin(String email, String password) {
        LocalDateTime myDateObj=LocalDateTime.now();
        DateTimeFormatter myFormatterObj=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate=myDateObj.format(myFormatterObj);


        if (!verifyGymOwner(email, password)) {
            return false;
        }


        System.out.println( "Login Successful! (Gym Owner)" );
        FlipfitGymOwner owner = ownerService.getProfile(email, password);
        System.out.println("Gym Owner " + owner.getOwnerName() +  " Logged In At "+formattedDate);
        // show username



        while (true) {
            System.out.println(  "-----------------Gym Owner Menu-----------------" );
            System.out.println( "Press 1 to add a gym");
            System.out.println("Press 2 to update gym details");
            System.out.println("Press 3 to view all gyms");
            System.out.println("Press 4 to add slots");
            System.out.println("Press 5 to update seat count by");
            System.out.println("Press 6 to update your details");
            System.out.println("Press 7 to view profile");
            System.out.println("Press 8 to logout" );

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
                    if (updateGymOwnerDetails())
                        System.out.println("Gym owner updated successfully!" );
                    else
                        System.out.println("Gym owner not updated" );
                    break;
                case 7:
                    viewProfile(email, password);
                    break;
                case 8:
                    return true;
                default:
                    System.out.println("Invalid option!" );
            }
        }
    }

    /// Adds a new gym to the system.
    /// @param email Gym owner's email address (used to fetch the owner ID).
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

    public void addSlots() {
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

    /// Updates the gym owner's password.
    /// @param userMail Gym owner's email address.
    /// @param password Current password.
    /// @param updatedPassword New password.
    /// @return true if the password update is successful, false otherwise.
//    public boolean updatePassword(String userMail, String password, String updatedPassword) {
//        return ownerService.updateGymOwnerPassword(userMail, password, updatedPassword);
//    }

    /// Displays all gyms owned by the logged-in gym owner.
    /// @param email Gym owner's email address.
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

    /// Updates the gym owner's password.
    /// @param userMail Gym owner's email address.
    /// @param password Current password.
    /// @param updatedPassword New password.
    /// @return true if the password update is successful, false otherwise.
    public boolean updatePassword(String userMail, String password, String updatedPassword) {
        return ownerService.updateGymOwnerPassword(userMail, password, updatedPassword);
    }

    /**
     * Updates the seat count for a specific gym and slot.
     */
    public void updateSeatCount(String email) {
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

    /// Updates the details of an existing gym owner.
    /// @return true if the update is successful, false otherwise.
    private boolean updateGymOwnerDetails() {
        System.out.println( "Enter gym owner details:" );
        System.out.println( "Email: " );
        String ownerEmail = scanner.nextLine();
       
        System.out.println( "Name: " );
        String ownerName = scanner.nextLine();
        System.out.println( "Phone Number: " );
        String phoneNo = scanner.nextLine();
        

        FlipfitGymOwner gymOwner = new FlipfitGymOwner();
        gymOwner.setOwnerEmail(ownerEmail);
        gymOwner.setOwnerName(ownerName);
        gymOwner.setPhoneNo(phoneNo);

        return ownerService.updateGymOwner(gymOwner);
    }

    /**
     * Handles the creation of a new gym owner account.
     */
    public void createGymOwner() {
        System.out.println("Enter gym owner details:" );
        System.out.println( "Email: " );
        String ownerEmail = scanner.nextLine();

        System.out.println( "Name: " );
        String ownerName = scanner.nextLine();
        System.out.println( "Password: " );
        String password = scanner.nextLine();

        System.out.println( "Phone Number: " );
        String phoneNo = scanner.nextLine();

        System.out.println( "National ID: " );
        String nationalId = scanner.nextLine();


        if (nationalId.length() != 12) {
            System.out.println( "Invalid national ID! Length must be 12" );
            return;
        }

        System.out.println( "GST: " );
        String GST = scanner.nextLine();

        System.out.println( "PAN Number: " );
        String PAN = scanner.nextLine();

        if (PAN.length() != 10) {
            System.out.println( "Invalid PAN Card Number. Length must be 10" );
            return;
        }

        FlipfitGymOwner gymOwner = new FlipfitGymOwner();
        List<FlipfitGymCenter> emptyGymList = new ArrayList<>();
        gymOwner.setOwnerEmail(ownerEmail);
        gymOwner.setPAN(PAN);
        gymOwner.setOwnerName(ownerName);
        gymOwner.setGST(GST);
        gymOwner.setPassword(password);
        gymOwner.setNationalId(nationalId);
        gymOwner.setPhoneNo(phoneNo);
        gymOwner.setGyms(emptyGymList);
        gymOwner.setVerificationStatus("unverified");

        if (ownerService.createGymOwner(gymOwner)) {
            System.out.println( "Gym owner created!" );
        } else {
            System.out.println( "Gym owner not created!" );
        }
    }
    
}

