package com.flipkart.constant;

/**
 * This class defines SQL queries used throughout the application for interacting with the database.
 * The constants represent various SQL statements for operations like insert, update, and select on tables like FlipfitGymCenter, Slots, FlipfitCustomer, FlipfitGymOwner, and Bookings.
 *
  */
public class SQLConstants {

    // Insert a new gym with provided details
    public static final String GYM_OWNER_INSERT_GYM = "INSERT INTO FlipfitGymCenter (gymName, gymAddress, location, ownerId, Status) VALUES(?, ?, ?, ?, ?)";

    // Insert new slots for a gym
    public static final String GYM_OWNER_ADD_SLOTS = "INSERT INTO Slots (startTime, seatCount, gymId) VALUES (?, ?, ?)";

    // Update user password based on their email and current password
    public static final String GYM_USER_UPDATE_PASSWORD = "UPDATE FlipfitCustomer SET password = ? WHERE email = ? AND password = ?";

    // Update gym owner's password
    public static final String ADMIN_PWD_UPDATE="UPDATE FlipfitAdmin SET password = ?";
    // Update gym owner's password based on their email and current password
    public static final String GYM_OWNER_UPDATE_PASSWORD = "UPDATE FlipfitGymOwner SET password = ? WHERE ownerEmail = ? AND password = ?";
    // all admin details
    public static final String ALL_ADMIN_DETAILS="SELECT * FROM FlipfitAdmin";
    // Verify gym owner credentials based on email and password
    public static final String GYM_OWNER_VERIFY_PASSWORD = "SELECT * FROM FlipfitGymOwner WHERE ownerEmail = ? AND password = ?";

    // FlipfitAdmin: View all users in the system
    public static final String ADMIN_VIEW_ALL_USERS = "SELECT * FROM FlipfitCustomer";

    // FlipfitAdmin: View all gyms in the system
    public static final String ADMIN_VIEW_ALL_GYMS = "SELECT * FROM FlipfitGymCenter";

    // FlipfitAdmin: View all gym owners in the system
    public static final String ADMIN_VIEW_ALL_GYM_OWNERS = "SELECT * FROM FlipfitGymOwner";

    // FlipfitAdmin: Verify a gym by its ID
    public static final String ADMIN_VERIFY_GYM = "UPDATE FlipfitGymCenter SET Status = 'verified' WHERE gymId = ?";

    // FlipfitAdmin: Verify a gym owner by their ID
    public static final String ADMIN_VERIFY_GYM_OWNER = "UPDATE FlipfitGymOwner SET verificationStatus = 'verified' WHERE ownerId = ?";

    // FlipfitAdmin: View all unverified gyms
    public static final String ADMIN_VIEW_UNVERIFIED_GYMS = "SELECT * FROM FlipfitGymCenter WHERE Status = 'unverified'";

    // FlipfitAdmin: View all unverified gym owners
    public static final String ADMIN_VIEW_UNVERIFIED_GYM_OWNERS = "SELECT * FROM FlipfitGymOwner WHERE verificationStatus = 'unverified'";

    // Insert a new gym owner into the system
    public static final String INSERT_GYM_OWNER = "INSERT INTO FlipfitGymOwner (ownerName, ownerEmail, password, phoneNo, nationalId, GST, PAN, verificationStatus) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    // FlipfitGymCenter owner: View all gyms managed by a specific owner
    public static final String GYM_OWNER_VIEW_GYMS = "SELECT * FROM FlipfitGymCenter WHERE ownerId = ?";

    // Get slots by gym ID
    public static final String GET_SLOTS_BY_GYM_ID = "SELECT * FROM Slots WHERE gymId = ?";

    // Update gym owner's details (name, phone number)
    public static final String UPDATE_GYM_OWNER = "UPDATE FlipfitGymOwner SET ownerName = ?, phoneNo = ? WHERE ownerEmail = ?";

    // Update user's details (name, phone number)
    public static final String UPDATE_USER = "UPDATE FlipfitCustomer SET userName = ?, phoneNumber = ? WHERE email = ?";

    // Update gym's details (name, address, location)
    public static final String UPDATE_GYM = "UPDATE FlipfitGymCenter SET location = ?, gymAddress = ?, gymName = ? Where gymId = ?";

    // Update seat count for a gym's slot
    public static final String GYM_OWNER_UPDATE_SEAT_COUNT = "UPDATE Slots SET seatCount = seatCount + ? WHERE startTime = ? AND gymId = ?";

    // Get the seat count of a specific slot
    public static final String GET_SEAT_COUNT = "SELECT seatCount FROM Slots WHERE gymId = ? AND startTime = ?";

    // Get gym owner ID based on their email
    public static final String GET_GYM_OWNER_ID_BY_EMAIL = "SELECT ownerId FROM FlipfitGymOwner WHERE ownerEmail = ?";

    // Insert a new user into the system
    public static final String INSERT_USER = "INSERT INTO FlipfitCustomer (userName, phoneNumber, address, location, email, password) VALUES (?, ?, ?, ?, ?, ?)";

    // Verify user password
    public static final String VERIFY_USER_PASSWORD = "SELECT * FROM FlipfitCustomer WHERE email = ? and password = ?";

    // Get user ID based on their email
    public static final String GET_USER_ID_BY_EMAIL = "SELECT userId FROM FlipfitCustomer WHERE email = ?";

    // Get gyms by area (address or location)
    public static final String GET_GYMS_BY_AREA = "SELECT * FROM FlipfitGymCenter WHERE gymAddress LIKE CONCAT( '%',?,'%') OR location LIKE CONCAT( '%',?,'%')";

    // Get slot ID by gym ID and start time
    public static final String GET_SLOTS_ID_BY_GYM_ID_AND_START_TIME = "SELECT slotsId FROM Slots WHERE gymId = ? AND startTime = ?";

    // Get gyms that have available slots
    public static final String GET_GYMS_WITH_SLOTS = "SELECT * FROM FlipfitGymCenter WHERE gymId IN (SELECT gymId FROM Slots WHERE seatCount > 0)";

    // Insert a new booking into the system
    public static final String INSERT_BOOKING = "INSERT INTO Bookings (userId, bookingStatus, time, slotId, gymId) VALUES (?, ?, ?, ?, ?)";

    //update customer profile
    public static final String UPDATE_CUSTOMER_PROFILE ="UPDATE FlipfitCustomer SET email = ?, password = ?, userName = ?, phoneNumber = ?, address = ?, location = ? WHERE userId = ?";

    // Get bookings by user ID
    public static final String GET_BOOKINGS_BY_USER_ID = "SELECT * FROM Bookings WHERE userId = ?";

    // Update the status of a booking
    public static final String UPDATE_BOOKING_STATUS = "UPDATE Bookings SET bookingStatus = ? WHERE bookingId = ?";

    // Get a booking by booking ID
    public static final String GET_BOOKING_BY_BOOKING_ID = "SELECT * FROM Bookings WHERE bookingId = ?";

    // Update status of overlapping bookings
    public static final String UPDATE_OVERLAPPING_BOOKING_STATUS = "UPDATE Bookings SET bookingStatus = ? WHERE userId = ? AND gymId = ? AND time = ? AND bookingStatus = ?";
}

