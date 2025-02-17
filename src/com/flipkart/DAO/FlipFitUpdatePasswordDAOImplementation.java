package com.flipkart.DAO;

import com.flipkart.constant.SQLConstants;
import com.flipkart.exception.WrongCredentialsException;
import com.flipkart.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FlipFitUpdatePasswordDAOImplementation implements FlipFitUpdatePasswordDAOInterface {

    /**
     * Updates the gym owner's password in the database.
     * This method validates the current password and updates it to the new password if the credentials are correct.
     *
     * @param email The email address of the gym owner whose password is to be updated.
     * @param password The current password of the gym owner.
     * @param updatedPassword The new password to be set for the gym owner.
     * @return true if the password is updated successfully, false if the credentials are incorrect or there is an error.
     */
    public boolean updateGymOwnerPassword(String email, String password, String updatedPassword) {
        try (Connection conn = DatabaseConnector.getConnection();
             Statement statement = conn.createStatement();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.GYM_OWNER_UPDATE_PASSWORD, statement.RETURN_GENERATED_KEYS);) {

            preparedStatement.setString(1, updatedPassword);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                // Password updated successfully
                return true;
            } else {
                // Throw an exception if the credentials are wrong
                throw new WrongCredentialsException();
            }
        } catch (WrongCredentialsException e) {
            // Handle wrong credentials exception
            return false;
        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Updates the gym user's password in the database.
     * This method validates the current password and updates it to the new password if the credentials are correct.
     *
     * @param email The email address of the gym user whose password is to be updated.
     * @param password The current password of the gym user.
     * @param updatedPassword The new password to be set for the gym user.
     * @return true if the password is updated successfully, false if the credentials are incorrect or there is an error.
     */
    public boolean updateGymUserPassword(String email, String password, String updatedPassword) {
        try (Connection conn = DatabaseConnector.getConnection();
             Statement statement = conn.createStatement();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.GYM_USER_UPDATE_PASSWORD);) {

            preparedStatement.setString(1, updatedPassword);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                // Password updated successfully
                return true;
            } else {
                // Throw an exception if the credentials are wrong
                throw new WrongCredentialsException();
            }
        } catch (WrongCredentialsException e) {
            // Handle wrong credentials exception
            return false;
        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println(e.getMessage());
            return false;
        }
    }
}
