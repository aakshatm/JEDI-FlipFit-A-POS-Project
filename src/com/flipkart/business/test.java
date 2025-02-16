package com.flipkart.business;

import com.flipkart.bean.*;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
//        FlipfitAdminInterface adminService = new FlipfitAdminService();
//        System.out.println(adminService.login(101, "Election77@7"));
//        adminService.editProfile(101, "random");

        FlipfitGymCustomerInterface gymcustomerService = new FlipfitGymCustomerService();
        System.out.println(gymcustomerService.getSeatCount(1, 6));
        gymcustomerService.bookSlot(1, 6, "aakshatmalhotra100@gmail.com"); // issue in booking
//        for (Booking book: gymcustomerService.viewAllBookings(2)){
//            System.out.println(book.getBookingId());
//        }
//        FlipfitGymOwnerInterface ownerService = new FlipfitGymOwnerService();
//        private int gymId;
//
//        /**
//         * Name of the gym.
//         */
//        private String gymName;
//
//        /**
//         * Address of the gym.
//         */
//        private String gymAddress;
//
//        /**
//         * Location of the gym.
//         */
//        private String location;
//
//        /**
//         * List of slots available in the gym.
//         */
//        private List<Slot> slots;
//
//        /**
//         * Unique identifier for the owner of the gym.
//         */
//        private int ownerId;
//
//        /**
//         * Status of the gym (e.g., open, closed).
//         */
//        private String Status;
//
//        /**
//         * Gets the unique identifier for the owner of the gym.
//         *

//         FlipfitGymCenter gym = new FlipfitGymCenter();
//         gym.setGymId(101);
//         gym.setGymName("aAakshat Gym");
//         gym.setGymAddress("asdfasdfasdf");
//         gym.setLocation("Delhi");
//         gym.setOwnerId(1);
//         gym.setStatus("unverified");
//         Slot st = new Slot(101, 1, 23);
//         List<Slot> slotList = new ArrayList<>();
//         slotList.add(st);
//         gym.setSlots(slotList);
//
//        System.out.println(ownerService.addGym(gym));
//        FlipfitAdminInterface adminService = new FlipfitAdminService();
//        for (FlipfitGymCenter gym: adminService.getUnverifiedGyms()){
//            System.out.println(gym.getGymId());
//            System.out.println(gym.getGymName());
//        }
//
//        adminService.verifyGym(4);
//
//        for (FlipfitGymCenter gym: adminService.getUnverifiedGyms()){
//            System.out.println(gym.getGymId());
//            System.out.println(gym.getGymName());
//        }

    }
}
