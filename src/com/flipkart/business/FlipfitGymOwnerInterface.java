package com.flipkart.business;

import com.flipkart.bean.FlipfitGymOwner;
import com.flipkart.bean.FlipfitGymCenter;
import com.flipkart.bean.Slot;

public interface FlipfitGymOwnerInterface {
    void registerGym(FlipfitGymOwner owner, FlipfitGymCenter gym);
    void viewRegisteredGyms(FlipfitGymOwner owner);
    void editGymDetails(FlipfitGymOwner owner, int gymId);
    void removeGym(FlipfitGymOwner owner, int gymId);
    void addSlots(FlipfitGymOwner owner, int gymId, Slot slot);
    void editSlots(FlipfitGymOwner owner, int gymId, int slotId, Slot newSlot);
    void removeSlot(FlipfitGymOwner owner, int gymId, int slotId);
    void viewApprovedGyms(FlipfitGymOwner owner);
    void viewPendingApprovals(FlipfitGymOwner owner);
}
