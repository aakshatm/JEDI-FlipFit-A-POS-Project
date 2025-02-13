package com.flipkart.business;

import com.flipkart.bean.FlipfitGymOwner;

public interface FlipfitAdminInterface {
    void login(FlipfitGymOwner user);
    void logout(FlipfitGymOwner user);
    void viewProfile(FlipfitGymOwner user);
    void editProfile(FlipfitGymOwner user);
    void register();
    void approveGym();
    void viewRegisteredGym();
    void viewRegisteredCustomers();
    void viewPendingApprovals();
}
