package com.flipkart.DAO;

import com.flipkart.bean.FlipfitGymCenter;
import com.flipkart.bean.FlipfitGymOwner;
import com.flipkart.bean.Slot;
import com.flipkart.constant.SQLConstants;
import com.flipkart.exception.RegistrationFailedException;
import com.flipkart.exception.SlotInsertionFailedException;
import com.flipkart.exception.UpdationFailedException;
import com.flipkart.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlipFitGymOwnerDAOImplementation implements FlipFitGymOwnerDAOInterface {

    /**
     * Retrieves the profile information of a gym owner based on the provided email and password.
     * This method validates the credentials and returns the gym owner's profile details if the credentials match.
     *
     * @param email The email address of the gym owner whose profile is to be retrieved.
     * @param password The password of the gym owner to validate the credentials.
     * @return A `FlipfitGymOwner` object containing the profile details of the gym owner if credentials are valid,
     *         or `null` if no matching record is found or if there is a SQL error.
     */
    public FlipfitGymOwner getProfile(String email, String password) {
        FlipfitGymOwner gymOwner = null;

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.GYM_OWNER_VERIFY_PASSWORD)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) { // Check if there is a matching record
                    gymOwner = new FlipfitGymOwner();
                    gymOwner.setOwnerId(resultSet.getInt("ownerId"));
                    gymOwner.setOwnerName(resultSet.getString("ownerName"));
                    gymOwner.setOwnerEmail(resultSet.getString("ownerEmail"));
                    gymOwner.setPassword(resultSet.getString("password"));
                    gymOwner.setPhoneNo(resultSet.getString("phoneNo"));
                    gymOwner.setNationalId(resultSet.getString("nationalId"));
                    gymOwner.setGST(resultSet.getString("GST"));
                    gymOwner.setPAN(resultSet.getString("PAN"));
                    gymOwner.setVerificationStatus(resultSet.getString("verificationStatus"));
                    // Populate other fields as necessary
                    return gymOwner;
                }
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }

        return null; // Returns null if no record is found
    }

    /**
     * Adds a new gym to the database with the provided details. The gym information includes name, address, location,
     * owner ID, and status. If the gym is successfully added, the method proceeds to add the slots associated with the gym.
     *
     * @param gym The `FlipfitGymCenter` object containing the gym's details to be added.
     * @return `true` if the gym and its associated slots are successfully added to the database;
     *         `false` if the registration fails or an error occurs during the process.
     */
    @Override
    public boolean addGym(FlipfitGymCenter gym) {
        int gymId = -1;

        try (Connection conn = DatabaseConnector.getConnection();
             Statement statement = conn.createStatement();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.GYM_OWNER_INSERT_GYM, statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, gym.getGymName());
            preparedStatement.setString(2, gym.getGymAddress());
            preparedStatement.setString(3, gym.getLocation());
            preparedStatement.setInt(4, gym.getOwnerId());
            preparedStatement.setString(5, gym.getStatus());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted == 0) {
                throw new RegistrationFailedException();
            }

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    gymId = resultSet.getInt(1);
                }
            }
        } catch (RegistrationFailedException e) {
            // System.out.println("FlipfitGymCenter " + e.getMessage());
            return false;
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
        return addSlots(gymId, gym.getSlots());
    }

    /**
     * Adds a list of slots for a specific gym to the database. Each slot includes start time, seat count, and the gym ID.
     * If any slot insertion fails, the method returns `false`. If all slots are successfully added, it returns `true`.
     *
     * @param gymId The ID of the gym to which the slots will be added.
     * @param slots A list of `Slot` objects representing the slots to be added.
     * @return `true` if all slots are successfully added to the database;
     *         `false` if any slot insertion fails or an error occurs during the process.
     */
    @Override
    public boolean addSlots(int gymId, List<Slot> slots) {
        for (Slot slot : slots) {
            try (Connection conn = DatabaseConnector.getConnection();
                 PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.GYM_OWNER_ADD_SLOTS);
            ) {
                preparedStatement.setInt(1, slot.getStartTime());
                preparedStatement.setInt(2, slot.getSeatCount());
                preparedStatement.setInt(3, gymId);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    // System.out.println("Record inserted successfully!");
                } else {
                    throw new SlotInsertionFailedException();
                }
            } catch (SlotInsertionFailedException e) {
                // System.out.println(e.getMessage());
                return false;
            } catch (SQLException e) {
                System.out.println("SQL Error: " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    /**
     * Registers a new gym owner in the system by adding their details to the database.
     * The owner's information, such as name, email, password, contact details,
     * and identification numbers, are inserted into the database with a default status of "unverified".
     * If the registration fails for any reason, the method returns `false`.
     * If the owner is successfully registered, it returns `true`.
     *
     * @param gymOwner An instance of `FlipfitGymOwner` containing the owner's details to be registered.
     * @return `true` if the gym owner was successfully registered; `false` if the registration failed.
     */
    @Override
    public boolean createGymOwner(FlipfitGymOwner gymOwner) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.INSERT_GYM_OWNER);
        ) {
            preparedStatement.setString(1, gymOwner.getOwnerName());
            preparedStatement.setString(2, gymOwner.getOwnerEmail());
            preparedStatement.setString(3, gymOwner.getPassword());
            preparedStatement.setString(4, gymOwner.getPhoneNo());
            preparedStatement.setString(5, gymOwner.getNationalId());
            preparedStatement.setString(6, gymOwner.getGST());
            preparedStatement.setString(7, gymOwner.getPAN());
            preparedStatement.setString(8, "unverified");

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                // System.out.println("Record inserted successfully!");
                return true;
            } else {
                throw new RegistrationFailedException();
            }
        } catch (RegistrationFailedException e) {
            // System.out.println("FlipfitGymCenter owner " + e.getMessage());
            return false;
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Updates the details of an existing gym owner in the database.
     * This method allows updating the gym owner's name, phone number, and email.
     * If the update is successful, it returns `true`. If no rows are updated or if the update fails,
     * it returns `false`.
     *
     * @param gymOwner An instance of `FlipfitGymOwner` containing the updated details of the gym owner.
     * @return `true` if the gym owner's details were successfully updated; `false` if the update failed.
     */
    @Override
    public boolean updateGymOwner(FlipfitGymOwner gymOwner) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.UPDATE_GYM_OWNER);
        ) {
            preparedStatement.setString(1, gymOwner.getOwnerName());
            preparedStatement.setString(2, gymOwner.getPhoneNo());
            preparedStatement.setString(3, gymOwner.getOwnerEmail());

            preparedStatement.executeUpdate();

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
            // System.out.println("FlipfitGymCenter owner " + e.getMessage());
            return false;
        }
    }

    /**
     * Validates the login credentials of a gym owner by checking the provided email and password against the database.
     * If the email and password match an existing gym owner, the method returns `true`. Otherwise, it returns `false`.
     *
     * @param email The email address of the gym owner.
     * @param password The password of the gym owner.
     * @return `true` if the credentials are valid; `false` otherwise.
     */
    @Override
    public boolean validateGymOwner(String email, String password) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.GYM_OWNER_VERIFY_PASSWORD);
        ) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
        return false;
    }

    /**
     * Retrieves a list of gyms associated with a specific gym owner identified by the owner ID.
     * The method queries the database for all gyms belonging to the owner and populates a list of `FlipfitGymCenter` objects.
     * Additionally, it fetches the available slots for each gym.
     *
     * @param ownerId The ID of the gym owner.
     * @return A list of `FlipfitGymCenter` objects representing the gyms owned by the gym owner.
     */
    @Override
    public List<FlipfitGymCenter> viewMyGyms(int ownerId) {
        List<FlipfitGymCenter> gyms = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.GYM_OWNER_VIEW_GYMS);
        ) {
            preparedStatement.setInt(1, ownerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int gymId = resultSet.getInt("gymId");
                    String gymAddress = resultSet.getString("gymAddress");
                    String location = resultSet.getString("location");
                    String gymName = resultSet.getString("gymName");
                    String status = resultSet.getString("Status");
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

        for (FlipfitGymCenter gym : gyms) {
            List<Slot> slots = getSlotsByGymId(gym.getGymId());
            gym.setSlots(slots);
        }
        return gyms;
    }

    /**
     * Retrieves the list of available slots for a given gym based on the gym's ID.
     * This method queries the database to fetch all slots (start time and seat count) associated with the specified gym.
     *
     * @param gymId The ID of the gym.
     * @return A list of `Slot` objects representing the available slots for the gym.
     */
    public List<Slot> getSlotsByGymId(int gymId) {
        List<Slot> slotList = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.GET_SLOTS_BY_GYM_ID);
        ) {
            preparedStatement.setInt(1, gymId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int slotsId = resultSet.getInt("slotsId");
                    int startTime = resultSet.getInt("startTime");
                    int seatCount = resultSet.getInt("seatCount");
                    Slot slots = new Slot(slotsId, startTime, seatCount);

                    slotList.add(slots);
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return slotList;
    }

    /**
     * Updates the seat count for a specific gym and slot based on the provided gym ID and start time.
     * If the update is successful, the method returns `true`. If no rows are updated or an exception occurs,
     * it returns `false`.
     *
     * @param gymId The ID of the gym.
     * @param startTime The start time of the slot.
     * @param seatCount The new seat count to be updated.
     * @return `true` if the seat count was updated successfully; `false` otherwise.
     */
    @Override
    public boolean updateSeatCount(int gymId, int startTime, int seatCount) {

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.GYM_OWNER_UPDATE_SEAT_COUNT);
        ) {
            preparedStatement.setInt(1, seatCount);
            preparedStatement.setInt(2, startTime);
            preparedStatement.setInt(3, gymId);

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
            // System.out.println("Seat count " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves the current seat count for a specific gym and slot based on the provided gym ID and start time.
     * If the seat count is found, it is returned. If an error occurs or no data is found, the method returns `-1`.
     *
     * @param gymId The ID of the gym.
     * @param startTime The start time of the slot.
     * @return The seat count for the specified gym and slot, or `-1` if an error occurs or no data is found.
     */
    public int getSeatCount(int gymId, int startTime) {
        int seatCount = -1;

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.GET_SEAT_COUNT);
        ) {
            preparedStatement.setInt(1, gymId);
            preparedStatement.setInt(2, startTime);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    seatCount = resultSet.getInt("seatCount");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
        return seatCount;
    }

    /**
     * Retrieves the gym owner's ID based on their email address.
     * The method queries the database and returns the owner ID if a matching record is found.
     * If no matching record is found or an error occurs, it returns `-1`.
     *
     * @param email The email address of the gym owner.
     * @return The owner ID associated with the provided email, or `-1` if not found or an error occurs.
     */
    public int getGymOwnerIdByEmail(String email) {
        int ownerId = -1;

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.GET_GYM_OWNER_ID_BY_EMAIL);
        ) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ownerId = resultSet.getInt("ownerId");
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return -1;
        }
        return ownerId;
    }

    /**
     * Updates the details of a gym (location, address, name) based on the provided `FlipfitGymCenter` object.
     * If the update is successful, the method returns `true`. If no rows are updated or an exception occurs,
     * it returns `false`.
     *
     * @param gym The `FlipfitGymCenter` object containing the updated gym details.
     * @return `true` if the gym details were updated successfully; `false` otherwise.
     */
    public boolean updateGymDetails(FlipfitGymCenter gym) {

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQLConstants.UPDATE_GYM);
        ) {
            preparedStatement.setString(1, gym.getLocation());
            preparedStatement.setString(2, gym.getGymAddress());
            preparedStatement.setString(3, gym.getGymName());
            preparedStatement.setInt(4, gym.getGymId());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("FlipfitGymCenter details updated successfully!");
                return true;
            } else {
                throw new UpdationFailedException();
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        } catch (UpdationFailedException e) {
            // System.out.println("FlipfitGymCenter " + e.getMessage());
            return false;
        }

    }
}