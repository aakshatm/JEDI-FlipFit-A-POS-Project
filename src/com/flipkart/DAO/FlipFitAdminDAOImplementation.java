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

/**
 * This class implements the FlipFitAdminDAOInterface and provides the functionality
 * for managing the admin actions in the FlipFit FlipfitGymCenter system, including viewing gyms,
 * customers, gym owners, updating the admin profile, and verifying gyms and gym owners.
 */
public class FlipFitAdminDAOImplementation implements FlipFitAdminDAOInterface {

    /**
     * Edits the admin's profile by updating the password.
     *
     * @param password The new password to update.
     * @return true if the update was successful, false otherwise.
     */
    @Override
    public boolean editProfile(String password){
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.ADMIN_PWD_UPDATE)) {

            preparedStatement.setString(1, password);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                throw new UpdationFailedException();
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        } catch (UpdationFailedException e) {
            return false;
        }
    }

    /**
     * Retrieves the details of the admin.
     *
     * @return the FlipfitAdmin object with the admin details, or null if not found.
     */
    public FlipfitAdmin getAdminDetails(){
        FlipfitAdmin admin = new FlipfitAdmin();
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.ALL_ADMIN_DETAILS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                admin.setAdminId(resultSet.getInt("adminId"));
                admin.setPassword(resultSet.getString("password"));
                admin.setName(resultSet.getString("name"));
                admin.setPhoneNumber(resultSet.getString("phoneNumber"));
                admin.setEmail(resultSet.getString("email"));
                return admin;
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves a list of all gyms managed in the system.
     *
     * @return a list of FlipfitGymCenter objects representing all gyms.
     */
    @Override
    public List<FlipfitGymCenter> viewGyms() {
        List<FlipfitGymCenter> gyms = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.ADMIN_VIEW_ALL_GYMS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                FlipfitGymCenter gym = new FlipfitGymCenter();
                gym.setGymId(resultSet.getInt("gymId"));
                gym.setGymName(resultSet.getString("gymName"));
                gym.setGymAddress(resultSet.getString("gymAddress"));
                gym.setOwnerId(resultSet.getInt("ownerId"));
                gym.setLocation(resultSet.getString("location"));
                gym.setStatus(resultSet.getString("Status"));
                gyms.add(gym);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return gyms;
    }

    /**
     * Retrieves a list of all customers in the system.
     *
     * @return a list of FlipfitCustomer objects representing all customers.
     */
    @Override
    public List<FlipfitCustomer> viewCustomers() {
        List<FlipfitCustomer> users = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.ADMIN_VIEW_ALL_USERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                FlipfitCustomer user = new FlipfitCustomer();
                user.setUserId(resultSet.getInt("userId"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                user.setUserName(resultSet.getString("userName"));
                user.setAddress(resultSet.getString("address"));
                user.setLocation(resultSet.getString("location"));
                user.setEmail(resultSet.getString("email"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return users;
    }

    /**
     * Retrieves a list of all gym owners in the system.
     *
     * @return a list of FlipfitGymOwner objects representing all gym owners.
     */
    @Override
    public List<FlipfitGymOwner> viewGymOwners() {
        List<FlipfitGymOwner> gymOwners = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.ADMIN_VIEW_ALL_GYM_OWNERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                FlipfitGymOwner gymOwner = new FlipfitGymOwner();
                gymOwner.setOwnerId(resultSet.getInt("ownerId"));
                gymOwner.setPhoneNo(resultSet.getString("phoneNo"));
                gymOwner.setOwnerName(resultSet.getString("ownerName"));
                gymOwner.setOwnerEmail(resultSet.getString("ownerEmail"));
                gymOwner.setNationalId(resultSet.getString("nationalId"));
                gymOwner.setGST(resultSet.getString("GST"));
                gymOwner.setPAN(resultSet.getString("PAN"));
                gymOwner.setVerificationStatus(resultSet.getString("verificationStatus"));
                gymOwners.add(gymOwner);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return gymOwners;
    }

    /**
     * Verifies a gym owner in the system.
     *
     * @param gymOwnerId The ID of the gym owner to be verified.
     * @return true if the verification was successful, false otherwise.
     */
    @Override
    public boolean verifyGymOwner(int gymOwnerId) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.ADMIN_VERIFY_GYM_OWNER)) {

            preparedStatement.setInt(1, gymOwnerId);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            } else {
                throw new VerificationFailedException();
            }
        } catch (VerificationFailedException | SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return false;
    }

    /**
     * Verifies a gym in the system.
     *
     * @param gymId The ID of the gym to be verified.
     * @return true if the verification was successful, false otherwise.
     */
    @Override
    public boolean verifyGym(int gymId) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.ADMIN_VERIFY_GYM)) {

            preparedStatement.setInt(1, gymId);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            } else {
                throw new VerificationFailedException();
            }
        } catch (VerificationFailedException | SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return false;
    }

    /**
     * Retrieves a list of all unverified gyms.
     *
     * @return a list of FlipfitGymCenter objects representing unverified gyms.
     */
    @Override
    public List<FlipfitGymCenter> getUnverifiedGyms() {
        List<FlipfitGymCenter> gyms = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.ADMIN_VIEW_UNVERIFIED_GYMS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                FlipfitGymCenter gym = new FlipfitGymCenter();
                gym.setGymId(resultSet.getInt("gymId"));
                gym.setGymName(resultSet.getString("gymName"));
                gym.setGymAddress(resultSet.getString("gymAddress"));
                gym.setOwnerId(resultSet.getInt("ownerId"));
                gym.setLocation(resultSet.getString("location"));
                gym.setStatus(resultSet.getString("Status"));
                gyms.add(gym);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return gyms;
    }

    /**
     * Retrieves a list of all unverified gym owners.
     *
     * @return a list of FlipfitGymOwner objects representing unverified gym owners.
     */
    @Override
    public List<FlipfitGymOwner> getUnverifiedGymOwners() {
        List<FlipfitGymOwner> gymOwners = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.ADMIN_VIEW_UNVERIFIED_GYM_OWNERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                FlipfitGymOwner gymOwner = new FlipfitGymOwner();
                gymOwner.setOwnerId(resultSet.getInt("ownerId"));
                gymOwner.setPhoneNo(resultSet.getString("phoneNo"));
                gymOwner.setOwnerName(resultSet.getString("ownerName"));
                gymOwner.setOwnerEmail(resultSet.getString("ownerEmail"));
                gymOwner.setNationalId(resultSet.getString("nationalId"));
                gymOwner.setGST(resultSet.getString("GST"));
                gymOwner.setPAN(resultSet.getString("PAN"));
                gymOwner.setVerificationStatus(resultSet.getString("verificationStatus"));
                gymOwners.add(gymOwner);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return gymOwners;
    }
}
