//package com.flipkart.business;
//
//import com.flipkart.bean.*;
//
//import java.util.*;
//
//public class FlipfitGymCustomerService {
//
//    private Map<Integer, FlipfitCustomer> customers = new HashMap<>();
//    private Map<Integer, FlipfitGymCenter> gyms = new HashMap<>();
//    private Map<Integer, Booking> bookings = new HashMap<>();
//
//    public List<FlipfitCustomer> registeredCustomers;
//
////    private int userId;
////    private String email;
////    private String name;
////    private String password;
////    private String contactNumber;
////private int age;
////    private List<Booking> bookings;
//
//    public FlipfitGymCustomerService(){
//        registeredCustomers = new ArrayList<>();
//    }
//    public boolean register(int userId, String email, String name, String password, String contactNumber){
//        FlipfitCustomer newUser = new FlipfitCustomer();
//        newUser.setUserId(userId);
//        newUser.setEmail(email);
//        newUser.setName(name);
//        newUser.setPassword(password);
//        newUser.setContactNumber(contactNumber);
//        registeredCustomers.add(newUser);
//        return true;
//    }
//
//    public boolean login(String email, String password){
//        for (int i = 0; i < registeredCustomers.size(); i++){
//            if (email.equals(registeredCustomers.get(i).getEmail()) && password.equals(registeredCustomers.get(i).getPassword())){
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    // View Available Slots
//    public boolean viewProfile(int userId, String email, String name, String password, String contactNumber){
//        for (int i = 0; i < registeredCustomers.size(); i++){
//            if (registeredCustomers.get(i).getUserId() == userId){
//                registeredCustomers.get(i).setUserId(userId);
//                registeredCustomers.get(i).setEmail(email);
//                registeredCustomers.get(i).setName(name);
//                registeredCustomers.get(i).setPassword(password);
//                registeredCustomers.get(i).setContactNumber(contactNumber);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    // edit profile
//    public FlipfitCustomer viewProfile(int userId){
//        for (int i = 0; i < registeredCustomers.size(); i++){
//            if (registeredCustomers.get(i).getUserId() == userId) {
//                return registeredCustomers.get(i);
//            }
//        }
//        return null;
//    }
//
//    // view all booking
//    public List<Booking> getAllBooking(int userId){
//        for (int i = 0; i < registeredCustomers.size(); i++){
//            if (registeredCustomers.get(i).getUserId() == userId){
//                return registeredCustomers.get(i).getBookings();
//            }
//        }
//
//        return null;
//    }
//
//
//    public void viewAvailableSlots(int centerId, String date) {
//        FlipfitGymCenter gym = gyms.get(centerId);
//        if (gym != null) {
//            System.out.println("Available Slots for " + gym.getGymName() + " on " + date + ":");
//            for (Slot slot : gym.getSlots()) {
//                System.out.println("Slot: " + slot.getStartTime() + " - " + slot.getEndTime() + " Capacity: " + slot.getCapacity());
//            }
//        } else {
//            System.out.println("Gym Center not found.");
//        }
//    }
//
//    // Find Gym by Location
//    public FlipfitGymCenter findGymByCity(String city, List<FlipfitGymCenter> listOfGyms) {
//        for (FlipfitGymCenter gym: listOfGyms){
//            if (gym.getGymCity().equals(city)){
//                return gym;
//            }
//        }
//        return null;
//    }
//
////    private int bookingId;
////    private int gymId;
////    private int slotId;
////    private boolean isConfirmed;
//
//
////    private int slotId;
////    private int startTime;
////    private int endTime;
////    private int capacity;
////    private List<Booking> bookings;
////    private List<Booking> waitlisted;
//
//    public boolean bookGymSlot(int bookingId, int gymId, int slotId,  List<FlipfitGymCenter> listOfGyms, List<Slot> slotList){
//        boolean bookingConfirmed = true;
//        Slot foundSlot = null;
//        for (Slot slot: slotList){
//            if (slot.getSlotId() == slotId){
//                if (slot.getBookings().size() >= slot.getCapacity()){
//                    foundSlot = slot;
//                    bookingConfirmed = false;
//                    break;
//                }
//            }
//        }
//        if (foundSlot == null) return false;
//
//        for (FlipfitGymCenter gym: listOfGyms){
//            if (gym.getGymId() == gymId){
//                Booking newBooking = new Booking();
//                newBooking.setBookingId(bookingId);
//                newBooking.setGymId(gymId);
//                newBooking.setSlot(slotId);
//                newBooking.setConfirmed(bookingConfirmed);
//                if (bookingConfirmed == true){
//                    foundSlot.getBookings().add(newBooking);
//                    return true;
//                }else{
//                    foundSlot.getWaitlisted().add(newBooking);
//                    return true;
//                }
//            }
//        }
//
//        return false;
//    }
//
//    // Make Payment
//    public void makePayment(int bookingId, Payment payment) {
//        Booking booking = bookings.get(bookingId);
//        if (booking != null && booking.isConfirmed()) {
//            System.out.println("Payment of " + payment.getAmount() + " using " + payment.getPaymentMode() + " made successfully for booking ID: " + bookingId);
//        } else {
//            System.out.println("Booking not confirmed. Unable to make payment.");
//        }
//    }
//
//    // Register for Waiting List
//    public void registerForWaitingList(int centerId, int slotId, FlipfitCustomer customer) {
//        FlipfitGymCenter gym = gyms.get(centerId);
//        if (gym != null) {
//            Slot slot = getSlotById(gym, slotId);
//            if (slot != null) {
//                if (slot.getBookings().size() >= slot.getCapacity()) {
//                    slot.getWaitlisted().add(new Booking()); // Add customer to the waitlist
//                    System.out.println(customer.getName() + " added to the waitlist for Slot ID: " + slotId);
//                } else {
//                    System.out.println("Slot not full. Booking available.");
//                }
//            }
//        }
//    }
//
//    // Book Gym Slot
//    public void bookGymSlot(FlipfitCustomer user, int slotId, int gymId) {
//        FlipfitGymCenter gym = gyms.get(gymId);
//        if (gym != null) {
//            Slot slot = getSlotById(gym, slotId);
//            if (slot != null && slot.getBookings().size() < slot.getCapacity()) {
//                Booking newBooking = new Booking();
//                newBooking.setBookingId(bookings.size() + 1);
//                newBooking.setGymId(gymId);
//                newBooking.setSlot(slotId);
//                newBooking.setConfirmed(true);
//
//                slot.getBookings().add(newBooking);
//                bookings.put(newBooking.getBookingId(), newBooking);
//                System.out.println(user.getName() + " booked Slot ID: " + slotId + " at Gym: " + gym.getGymName());
//            } else {
//                System.out.println("Slot is full or not available.");
//            }
//        }
//    }
//
//    // Cancel Booking
//    public boolean cancelBooking(int userId, int bookingId) {
//        for (int i = 0; i < registeredCustomers.size(); i++){
//            if (registeredCustomers.get(i).getUserId() == userId){
//                List<Booking> oldBookings =  registeredCustomers.get(i).getBookings();
//                List<Booking> newBookings = new ArrayList<>();
//                for (int j = 0; j < oldBookings.size(); j++){
//                    if (oldBookings.get(j).getBookingId() != bookingId){
//                        newBookings.add(oldBookings.get(j));
//                    }
//                }
//                registeredCustomers.get(i).setBookings(newBookings);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    // Helper method to get slot by ID
//    private Slot getSlotById(FlipfitGymCenter gym, int slotId) {
//        for (Slot slot : gym.getSlots()) {
//            if (slot.getStartTime() == slotId) {
//                return slot;
//            }
//        }
//        return null;
//    }
//}
