package com.flipkart.bean;

import java.util.ArrayList;
import java.util.List;

public class Slot {
    /**
     * Unique identifier for the time slot.
     */
    private int slotId;

    /**
     * Starting time of the time slot, represented as an integer (e.g., in minutes from midnight).
     */
    private int startTime;

    /**
     * Number of seats available in the time slot.
     */
    private int seatCount;

    /**
     * Constructs a Slots object with the specified slot ID, start time, and seat count.
     *
     * @param slotId    the unique identifier for the slot
     * @param startTime the starting time of the slot
     * @param seatCount the number of seats available in the slot
     */
    public Slot(int slotId, int startTime, int seatCount) {
        this.setSlotsId(slotId);
        this.setStartTime(startTime);
        this.setSeatCount(seatCount);
    }

    /**
     * Gets the unique identifier for the time slot.
     *
     * @return the slotsId, which is the unique identifier for the slot.
     */
    public int getSlotsId() {
        return slotId;
    }

    /**
     * Sets the unique identifier for the time slot.
     *
     * @param slotsId the slotsId to set, which is the unique identifier for the slot.
     */
    public void setSlotsId(int slotsId) {
        this.slotId = slotsId;
    }

    /**
     * Gets the starting time of the time slot.
     *
     * @return the startTime of the slot, represented as an integer.
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Sets the starting time of the time slot.
     *
     * @param startTime the startTime to set, represented as an integer.
     */
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the number of seats available in the time slot.
     *
     * @return the seatCount, which is the number of seats available in the slot.
     */
    public int getSeatCount() {
        return seatCount;
    }

    /**
     * Sets the number of seats available in the time slot.
     *
     * @param seatCount the seatCount to set, which is the number of seats available in the slot.
     */
    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    /**
     * Displays the details of the slot, including the slot ID, start time, and
     * the seat count available for the slot.
     */
    public void display() {
        System.out.println("--------------------------------------------------");
        System.out.println("Slot ID: " + slotId);
        System.out.println("Start Time: " + startTime);
        System.out.println("Seat Count: " + seatCount);
        System.out.println("--------------------------------------------------");
    }
}
