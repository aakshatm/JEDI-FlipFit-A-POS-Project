package com.flipkart.business;

import com.flipkart.bean.FlipfitGymOwner;

public class FlipfitAdminService implements FlipfitAdminInterface{

    public void login(FlipfitGymOwner user) {
        System.out.println(user.getName() + " logged in successfully!");
    }

    public void logout(FlipfitGymOwner user) {
        System.out.println(user.getName() + " logged out.");
    }

    public void viewProfile(FlipfitGymOwner user) {
        System.out.println("User Profile: " + user.getName() + " (Email: " + user.getEmail() + ")");
    }

    public void editProfile(FlipfitGymOwner user) {
        System.out.println("Edited User Profile: " + user.getName() + " (Email: " + user.getEmail() + ")");
    }

    public void register(){
        System.out.println("User Registered Successfully");
    }

    public void approveGym(){

    }
    public void viewRegisteredGym(){

    }
    public void viewRegisteredCustomers(){

    }
    public void viewPendingApprovals(){

    }
}
