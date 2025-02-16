package com.flipkart.DAO;

import com.flipkart.bean.FlipfitAdmin;
import com.flipkart.bean.FlipfitGymCenter;
import com.flipkart.bean.FlipfitGymOwner;
import com.flipkart.bean.FlipfitCustomer;
import com.flipkart.constant.SQLConstants;
import com.flipkart.exception.BookingCancellationFailedException;
import com.flipkart.exception.UpdationFailedException;
import com.flipkart.utils.DatabaseConnector;
import com.flipkart.exception.VerificationFailedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlipFitAdminDAOImplementation implements FlipFitAdminDAOInterface {

    @Override
    public boolean editProfile(String password){

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("UPDATE Admin SET password = ?")) {

            int bookingStatus = 2; // cancelled
            preparedStatement.setString(1, password);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                // System.out.println("Booking cancelled successfully!");
                return true;
            } else {
                throw new UpdationFailedException();
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        } catch (UpdationFailedException e) {
            // System.out.println(e.getMessage());
            return false;
        }

    }

    public FlipfitAdmin getAdminDetails(){
        FlipfitAdmin admin = new FlipfitAdmin();
        int adminId;
        String password;
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Admin");) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    adminId = resultSet.getInt("adminId");
                    password = resultSet.getString("password");
                    String name,phoneNumber, email;
                    name = resultSet.getString("name");
                    phoneNumber = resultSet.getString("phoneNumber");
                    email = resultSet.getString("email");

                    admin.setAdminId(adminId);
                    admin.setPassword(password);
                    admin.setName(name);
                    admin.setPhoneNumber(phoneNumber);
                    admin.setEmail(email);
                    return admin;
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return null;
    }
    @Override
    public List<FlipfitGymCenter> viewGyms() {
        List<FlipfitGymCenter> gyms = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.ADMIN_VIEW_ALL_GYMS);) {


            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int gymId = resultSet.getInt("gymId");
                    String gymAddress = resultSet.getString("gymAddress");
                    String location = resultSet.getString("location");
                    String gymName = resultSet.getString("gymName");
                    String status = resultSet.getString("Status");
                    int ownerId = resultSet.getInt("ownerId");
                    FlipfitGymCenter gym = new FlipfitGymCenter();
                    gym.setGymId(gymId);
                    gym.setGymName(gymName);
                    gym.setGymAddress(gymAddress);
                    gym.setOwnerId(ownerId);
                    gym.setLocation(location);
                    gym.setStatus(status);

                    gyms.add(gym);
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return gyms;
    }

    @Override
    public List<FlipfitCustomer> viewCustomers() {
        List<FlipfitCustomer> users = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.ADMIN_VIEW_ALL_USERS);) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int userId = resultSet.getInt("userId");
                    String phoneNumber = resultSet.getString("phoneNumber");
                    String userName = resultSet.getString("userName");
                    String address = resultSet.getString("address");
                    String location = resultSet.getString("location");
                    String email = resultSet.getString("email");

                    FlipfitCustomer user = new FlipfitCustomer();
                    user.setUserId(userId);
                    user.setPhoneNumber(phoneNumber);
                    user.setUserName(userName);
                    user.setAddress(address);
                    user.setLocation(location);
                    user.setEmail(email);

                    users.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return users;
    }

    @Override
    public List<FlipfitGymOwner> viewGymOwners() {
        List<FlipfitGymOwner> gymOwners = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.ADMIN_VIEW_ALL_GYM_OWNERS);) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int ownerId = resultSet.getInt("ownerId");
                    String phoneNo = resultSet.getString("phoneNo");
                    String ownerName = resultSet.getString("ownerName");
                    String ownerEmail = resultSet.getString("ownerEmail");
                    String nationalId = resultSet.getString("nationalId");
                    String GST = resultSet.getString("GST");
                    String PAN = resultSet.getString("PAN");
                    String verificationStatus = resultSet.getString("verificationStatus");

                    FlipfitGymOwner gymOwner = new FlipfitGymOwner();
                    gymOwner.setOwnerId(ownerId);
                    gymOwner.setPhoneNo(phoneNo);
                    gymOwner.setOwnerName(ownerName);
                    gymOwner.setOwnerEmail(ownerEmail);
                    gymOwner.setNationalId(nationalId);
                    gymOwner.setGST(GST);
                    gymOwner.setPAN(PAN);
                    gymOwner.setVerificationStatus(verificationStatus);

                    gymOwners.add(gymOwner);
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return gymOwners;
    }

    @Override
    public boolean verifyGymOwner(int gymOwnerId) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.ADMIN_VERIFY_GYM_OWNER);) {

            preparedStatement.setInt(1, gymOwnerId);

            int rowsUpdated = preparedStatement.executeUpdate(); // execute update statement
            if (rowsUpdated > 0) {
                // System.out.println("FlipfitGymCenter owner verified successfully!");
                return true;
            } else {
                throw new VerificationFailedException();
            }
        } catch (VerificationFailedException e) {
            // System.out.println("FlipfitGymCenter owner " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean verifyGym(int gymId) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.ADMIN_VERIFY_GYM);) {

            preparedStatement.setInt(1, gymId);

            int rowsUpdated = preparedStatement.executeUpdate(); // execute update statement
            if (rowsUpdated > 0) {
                // System.out.println("FlipfitGymCenter verified successfully!");
                return true;
            } else {
                throw new VerificationFailedException();
            }
        } catch (VerificationFailedException e) {
            // System.out.println("FlipfitGymCenter " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<FlipfitGymCenter> getUnverifiedGyms() {
        List<FlipfitGymCenter> gyms = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.ADMIN_VIEW_UNVERIFIED_GYMS);
             ResultSet resultSet = preparedStatement.executeQuery();) {

            while (resultSet.next()) {
                int gymId = resultSet.getInt("gymId");
                String gymAddress = resultSet.getString("gymAddress");
                String location = resultSet.getString("location");
                String gymName = resultSet.getString("gymName");
                String status = resultSet.getString("Status");
                int ownerId = resultSet.getInt("ownerId");

                FlipfitGymCenter gym = new FlipfitGymCenter();
                gym.setGymId(gymId);
                gym.setGymName(gymName);
                gym.setGymAddress(gymAddress);
                gym.setOwnerId(ownerId);
                gym.setLocation(location);
                gym.setStatus(status);

                gyms.add(gym);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return gyms;
    }

    @Override
    public List<FlipfitGymOwner> getUnverifiedGymOwners() {
        List<FlipfitGymOwner> gymOwners = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.ADMIN_VIEW_UNVERIFIED_GYM_OWNERS);
             ResultSet resultSet = preparedStatement.executeQuery();) {

            while (resultSet.next()) {
                int ownerId = resultSet.getInt("ownerId");
                String phoneNo = resultSet.getString("phoneNo");
                String ownerName = resultSet.getString("ownerName");
                String ownerEmail = resultSet.getString("ownerEmail");
                String nationalId = resultSet.getString("nationalId");
                String GST = resultSet.getString("GST");
                String PAN = resultSet.getString("PAN");
                String verificationStatus = resultSet.getString("verificationStatus");

                FlipfitGymOwner gymOwner = new FlipfitGymOwner();
                gymOwner.setOwnerId(ownerId);
                gymOwner.setPhoneNo(phoneNo);
                gymOwner.setOwnerName(ownerName);
                gymOwner.setOwnerEmail(ownerEmail);
                gymOwner.setNationalId(nationalId);
                gymOwner.setGST(GST);
                gymOwner.setPAN(PAN);
                gymOwner.setVerificationStatus(verificationStatus);

                gymOwners.add(gymOwner);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return gymOwners;
    }
}