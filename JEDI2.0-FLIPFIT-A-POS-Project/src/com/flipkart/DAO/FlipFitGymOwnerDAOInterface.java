package com.flipkart.DAO;

import com.flipkart.bean.FlipfitGymCenter;
import com.flipkart.bean.FlipfitGymOwner;
import com.flipkart.bean.Slot;

import java.util.List;

public interface FlipFitGymOwnerDAOInterface {

    FlipfitGymOwner getProfile(String email, String password);

    /**
     * Retrieves a list of gyms owned by a specific gym owner.
     * @param ownerId The ID of the gym owner whose gyms are to be retrieved. This should be a valid identifier for a gym owner in the system.
     * @return  A list of FlipfitGymCenter objects representing the gyms owned by the specified owner. If the owner has no gyms or the ID is invalid, an empty list is returned.
     */
    List<FlipfitGymCenter> viewMyGyms(int ownerId);

    /**
     * Creates a new gym owner in the system.
     * @param gymOwner The FlipfitGymOwner object containing the details of the new gym owner, including attributes such as name, contact information, and any other relevant data.
     * @return True if the gym owner was successfully created; returns false if the creation failed due to reasons such as validation errors or database issues.
     */

    boolean createGymOwner(FlipfitGymOwner gymOwner);

    /**
     * Updates the details of an existing gym owner in the system.
     * @param gymOwner The FlipfitGymOwner object containing the updated details of the gym owner, including attributes such as name, contact information, and any other relevant data that needs to be updated.
     * @return  True if the gym owner's details were successfully updated; returns false if the update failed due to reasons such as validation errors, non-existence of the owner, or database issues.
     */

    boolean updateGymOwner(FlipfitGymOwner gymOwner);

    /**
     * Adds a new gym to the system.
     * @param gym The FlipfitGymCenter object containing the details of the new gym, including attributes such as name, location, facilities, and other relevant information.
     * @return True if the gym was successfully added to the system; returns false if the addition failed due to reasons such as validation errors, duplicate entries, or database issues.
     */
    boolean addGym(FlipfitGymCenter gym);

    /**
     * Updates the number of available seats for a specific gym at a given time.
     * @param gymId The ID of the gym for which the seat count is to be updated. This should be a valid identifier for a gym in the system.
     * @param startTime The start time for which the seat count is being updated. This should be represented in a format that specifies the time slot or period (e.g., a timestamp or hour of the day).
     * @param seatCount The new number of available seats to be set for the specified gym and time. This should be a non-negative integer representing the updated seat availability.
     * @return  True if the seat count was successfully updated; returns false if the update failed due to reasons such as invalid gym ID, invalid time, or database issues.
     */

    boolean updateSeatCount(int gymId, int startTime, int seatCount);

    /**
     * Retrieves the number of available seats for a specific gym at a given time.
     * @param gymId The ID of the gym for which the seat count is being queried. This should be a valid identifier for a gym in the system.
     * @param startTime The start time for which the seat count is being queried. This should be represented in a format that specifies the time slot or period (e.g., a timestamp or hour of the day).
     * @return Returns the number of available seats for the specified gym and time. If the gym ID or time is invalid or if no data is available, a negative value (e.g., -1) may be returned to indicate an error or unavailability.
     */

    int getSeatCount(int gymId, int startTime);

    /**
     * Adds new time slots to a specific gym's schedule.
     * @param gymId The ID of the gym to which the slots are being added. This should be a valid identifier for a gym in the system.
     * @param slots A list of Slot objects representing the new time slots to be added. Each Slot object should include details such as start time, end time, and available seats.
     * @return  True if the slots were successfully added to the gym's schedule; returns false if the addition failed due to reasons such as invalid gym ID, invalid slot details, or database issues.
     */

    boolean addSlots(int gymId, List<Slot> slots);

    /**
     * Validates the credentials of a gym owner based on their email and password.
     * @param email The email address of the gym owner to be validated. This should be a valid and registered email address associated with a gym owner account.
     * @param password The password of the gym owner to be validated. This should be the correct password associated with the given email address.
     * @return  True if the provided email and password match the credentials of a registered gym owner; returns false if the credentials are invalid or do not match any registered gym owner.
     */

    boolean validateGymOwner(String email, String password);

    /**
     * Retrieves the list of time slots available for a specific gym.
     * @param gymId The ID of the gym whose time slots are to be retrieved. This should be a valid identifier for a gym in the system.
     * @return A list of Slot objects representing all available time slots for the specified gym. If no slots are found or the gym ID is invalid, an empty list is returned.
     */

    List<Slot> getSlotsByGymId(int gymId);

    /**
     * Retrieves the ID of a gym owner based on their email address.
     * @param email The email address of the gym owner whose ID is to be retrieved. This should be a valid and registered email address associated with a gym owner account.
     * @return  The ID of the gym owner associated with the provided email address. If no gym owner is found with the given email, a negative value (e.g., -1) is returned to indicate an error or non-existence.
     */

    int getGymOwnerIdByEmail(String email);

    /**
     * Updates a gym given the updated information
     * @param gym FlipfitGymCenter object with the updated details of the FlipfitGymCenter
     * @return  true if the update is successful, false otherwise
     */
    boolean updateGymDetails(FlipfitGymCenter gym);
}
