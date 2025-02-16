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

    public FlipfitGymOwner getProfile(String email, String password) {
        FlipfitGymOwner gymOwner = null;

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM GymOwner WHERE ownerEmail = ? AND password = ?")) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) { // Check if there is a matching record
                    gymOwner = new FlipfitGymOwner();
                    gymOwner.setOwnerId(resultSet.getInt("ownerId"));
                    gymOwner.setOwnerName(resultSet.getString("ownerEmail"));
                    gymOwner.setOwnerEmail(resultSet.getString("password"));
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