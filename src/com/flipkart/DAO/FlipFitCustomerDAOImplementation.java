package com.flipkart.DAO;

import com.flipkart.bean.Booking;
import com.flipkart.bean.FlipfitGymCenter;
import com.flipkart.bean.Slot;
import com.flipkart.bean.FlipfitCustomer;
import com.flipkart.constant.SQLConstants;
import com.flipkart.exception.*;
import com.flipkart.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlipFitCustomerDAOImplementation implements FlipFitCustomerDAOInterface {
    FlipFitGymOwnerDAOImplementation flipFitGymOwnerDAOImplementation = new FlipFitGymOwnerDAOImplementation();

    @Override
    public boolean editProfile(int customerId, String email, String password, String username, String phoneNumber, String address, String location) {
        String query = "UPDATE User SET email = ?, password = ?, userName = ?, phoneNumber = ?, address = ?, location = ? WHERE userId = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, location);
            preparedStatement.setInt(7, customerId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Return true if update is successful

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
    }

    public FlipfitCustomer getProfile(String email, String password){
        FlipfitCustomer profile = null;

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM User WHERE email = ? AND password = ?")) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    profile = new FlipfitCustomer();
                    profile.setUserId(resultSet.getInt("userId"));
                    profile.setUserName(resultSet.getString("userName"));
                    profile.setPhoneNumber(resultSet.getString("phoneNumber"));
                    profile.setAddress(resultSet.getString("address"));
                    profile.setLocation(resultSet.getString("location"));
                    profile.setEmail(resultSet.getString("email"));
                    profile.setPassword(resultSet.getString("password"));
                    return profile;
                } else {
                    throw new UserNotFoundException();
                }
            }
            catch (UserNotFoundException e){
                System.out.println("User details not found");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }

        return profile;
    }

    @Override
    public List<FlipfitGymCenter> viewAllGymsByArea(String area) {
        List<FlipfitGymCenter> gyms = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.GET_GYMS_BY_AREA);) {
            preparedStatement.setString(1, area);
            preparedStatement.setString(2, area);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int gymId = resultSet.getInt("gymId");
                    String gymAddress = resultSet.getString("gymAddress");
                    String location = resultSet.getString("location");
                    String gymName = resultSet.getString("gymName");
                    String status = resultSet.getString("Status");
                    int gymOwnerID = resultSet.getInt("ownerId");

                    if (status.equals("unverified")) continue;

                    FlipfitGymCenter gym = new FlipfitGymCenter();
                    gym.setGymName(gymName);
                    gym.setGymAddress(gymAddress);
                    gym.setOwnerId(gymOwnerID);
                    gym.setLocation(location);
                    gym.setStatus(status);
                    gym.setGymId(gymId);

                    gyms.add(gym);
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }

        for (FlipfitGymCenter gym : gyms) {
            List<Slot> slots = flipFitGymOwnerDAOImplementation.getSlotsByGymId(gym.getGymId());
            gym.setSlots(slots);
        }
        return gyms;
    }

    public List<FlipfitGymCenter> viewAllGymsWithSlots() {
        List<FlipfitGymCenter> gyms = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.GET_GYMS_WITH_SLOTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int gymId = resultSet.getInt("gymId");
                String gymAddress = resultSet.getString("gymAddress");
                String location = resultSet.getString("location");
                String gymName = resultSet.getString("gymName");
                String status = resultSet.getString("Status");
                int gymOwnerID = resultSet.getInt("ownerId");

                if (status.equals("unverified")) continue;

                FlipfitGymCenter gym = new FlipfitGymCenter();
                gym.setGymName(gymName);
                gym.setGymAddress(gymAddress);
                gym.setOwnerId(gymOwnerID);
                gym.setLocation(location);
                gym.setStatus(status);
                gym.setGymId(gymId);

                gyms.add(gym);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }

        for (FlipfitGymCenter gym : gyms) {
            List<Slot> slots = flipFitGymOwnerDAOImplementation.getSlotsByGymId(gym.getGymId());
            gym.setSlots(slots);
        }
        return gyms;
    }

    @Override
    public boolean bookSlot(int gymId, int startTime, String email) {
        boolean isBookingSuccessful = false;

        try {
            int seatCount = getSeatCount(gymId, startTime);
            if (seatCount == 0) {
                throw new SlotsUnavailableException();
            }
            if (seatCount == -1) {
                throw new SlotBookingFailedException();
            }

            int userId = getUserIdByEmail(email);
            if (userId == -1) {
                return false;
            }
            int bookingStatus = 1; // confirmed

            int slotId = getSlotsIdByGymIdAndStartTime(gymId, startTime);
            if (slotId == -1) {
                return false;
            }

//            if ((!cancelOverlappingBookingIfExists(gymId, startTime, userId, 0)) && (!cancelOverlappingBookingIfExists(gymId, startTime, userId, 1))) {
//                System.out.println("Unable to cancel the overlapping booking. Please try again");
//                return false;
//            }

            try (Connection conn = DatabaseConnector.getConnection();
                 PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.INSERT_BOOKING);) {

                // Set values for the placeholders in the prepared statement
                preparedStatement.setInt(1, userId);
                preparedStatement.setInt(2, bookingStatus);
                preparedStatement.setInt(3, startTime);
                preparedStatement.setInt(4, slotId);
                preparedStatement.setInt(5, gymId);

                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    isBookingSuccessful = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        } catch (SlotsUnavailableException | SlotBookingFailedException e) {
            System.out.println(e.getMessage());
            return false;
        }
        if (isBookingSuccessful) {
            // System.out.println("Record inserted successfully!");
            return flipFitGymOwnerDAOImplementation.updateSeatCount(gymId, startTime, -1);
        } else
            return false;
    }

    public int getSeatCount(int gymId, int startTime) {
        return flipFitGymOwnerDAOImplementation.getSeatCount(gymId, startTime);
    }

    public boolean cancelOverlappingBookingIfExists(int gymId, int startTime, int userId, int bookingStatus) {
        boolean isBookingCancelled = false;

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.UPDATE_OVERLAPPING_BOOKING_STATUS)) {

            int updatedBookingStatus = 2;
            preparedStatement.setInt(1, updatedBookingStatus);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, gymId);
            preparedStatement.setInt(4, startTime);
            preparedStatement.setInt(5, bookingStatus);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                isBookingCancelled = true; // issue
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
        if (isBookingCancelled) {
            // System.out.println("Booking cancelled successfully!");
            return flipFitGymOwnerDAOImplementation.updateSeatCount(gymId, startTime, 1);
        } else {
            return false;
        }
    }

    public int getSlotsIdByGymIdAndStartTime(int gymId, int startTime) {
        int slotId = -1;

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.GET_SLOTS_ID_BY_GYM_ID_AND_START_TIME);
        ) {
            preparedStatement.setInt(1, gymId);
            preparedStatement.setInt(2, startTime);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    slotId = resultSet.getInt("slotsId");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
        return slotId;
    }

    @Override
    public List<Booking> getAllBookingsByUserID(int userId) {
        List<Booking> bookings = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.GET_BOOKINGS_BY_USER_ID)) {

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Booking booking = new Booking();
                    booking.setBookingId(resultSet.getInt("bookingId"));
                    booking.setTime(resultSet.getInt("time"));
                    booking.setSlotId(resultSet.getInt("slotId"));
                    booking.setBookingStatus(resultSet.getInt("bookingStatus"));
                    booking.setGymId(resultSet.getInt("gymId"));
                    booking.setUserId(resultSet.getInt("userId"));
                    bookings.add(booking);
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return bookings;
    }

    @Override
    public boolean cancelBooking(int bookingId) {
        boolean isBookingCancelled = false;
        int gymId = -1;
        int startTime = -1;

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.UPDATE_BOOKING_STATUS)) {

            int bookingStatus = 2; // cancelled
            preparedStatement.setInt(1, bookingStatus);
            preparedStatement.setInt(2, bookingId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                // System.out.println("Booking cancelled successfully!");
                isBookingCancelled = true;
            } else {
                throw new BookingCancellationFailedException();
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        } catch (BookingCancellationFailedException e) {
            // System.out.println(e.getMessage());
            return false;
        }
        if (isBookingCancelled) {
            Booking booking = getBooking(bookingId);
            gymId = booking.getGymId();
            startTime = booking.getTime();
            return flipFitGymOwnerDAOImplementation.updateSeatCount(gymId, startTime, 1);
        } else
            return false;
    }

    public Booking getBooking(int bookingId) {
        Booking booking = new Booking();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.GET_BOOKING_BY_BOOKING_ID)) {

            preparedStatement.setInt(1, bookingId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    booking.setBookingId(resultSet.getInt("bookingId"));
                    booking.setTime(resultSet.getInt("time"));
                    booking.setSlotId(resultSet.getInt("slotId"));
                    booking.setBookingStatus(resultSet.getInt("bookingStatus"));
                    booking.setGymId(resultSet.getInt("gymId"));
                    booking.setUserId(resultSet.getInt("userId"));
                }
            } catch (SQLException e) {
                System.out.println("SQL Error: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return booking;
    }

    @Override
    public boolean validateUser(String username, String password) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.VERIFY_USER_PASSWORD)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
        return false;
    }

    @Override
    public boolean createUser(FlipfitCustomer user) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.INSERT_USER)) {

            // Set values for the placeholder in the prepared statement
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPhoneNumber());
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.setString(4, user.getLocation());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPassword());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                // System.out.println("FlipfitCustomer created successfully!");
                return true;
            } else {
                throw new RegistrationFailedException();
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        } catch (RegistrationFailedException e) {
            // System.out.println("FlipfitCustomer " + e.getMessage());
            return false;
        }
    }

    public boolean updateUserDetails(FlipfitCustomer user) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.UPDATE_USER);
        ) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPhoneNumber());
            preparedStatement.setString(3, user.getEmail());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                // System.out.println("Record updated successfully!");
                return true;
            } else {
                throw new UpdationFailedException();
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        } catch (UpdationFailedException e) {
            // System.out.println("FlipfitCustomer " + e.getMessage());
            return false;
        }
    }

    public int getUserIdByEmail(String email) {
        int userId = -1;

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.GET_USER_ID_BY_EMAIL);
        ) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userId = resultSet.getInt("userId");
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return -1;
        }
        return userId;
    }
}


