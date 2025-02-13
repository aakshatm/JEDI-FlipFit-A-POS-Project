package com.flipkart.business;

import com.flipkart.bean.FlipfitGymOwner;
import com.flipkart.bean.FlipfitGymCenter;
import com.flipkart.bean.Slot;

public class FlipfitGymOwnerService implements FlipfitGymOwnerInterface {

    public void registerGym(FlipfitGymOwner owner, FlipfitGymCenter gym) {
        // Method implementation here
    }

    public void viewRegisteredGyms(FlipfitGymOwner owner) {
        // Method implementation here
    }

    public void editGymDetails(FlipfitGymOwner owner, int gymId) {
        // Method implementation here
    }

    public void removeGym(FlipfitGymOwner owner, int gymId) {
        // Method implementation here
    }

    public void addSlots(FlipfitGymOwner owner, int gymId, Slot slot) {
        // Method implementation here
    }

    public void editSlots(FlipfitGymOwner owner, int gymId, int slotId, Slot newSlot) {
        // Method implementation here
    }

    public void removeSlot(FlipfitGymOwner owner, int gymId, int slotId) {
        // Method implementation here
    }

    public void viewApprovedGyms(FlipfitGymOwner owner) {
        // Method implementation here
    }

    public void viewPendingApprovals(FlipfitGymOwner owner) {
        // Method implementation here
    }
}
