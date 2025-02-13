package com.flipkart.business;

import com.flipkart.bean.FlipfitCustomer;
import com.flipkart.bean.User;

public class FlipfitAdminService {
    public void login(FlipfitCustomer user) {
        System.out.println(user.getName() + " logged in successfully!");
    }

    public void logout(FlipfitCustomer user) {
        System.out.println(user.getName() + " logged out.");
    }

    public void viewProfile(FlipfitCustomer user) {
        System.out.println("User Profile: " + user.getName() + " (Email: " + user.getEmail() + ")");
    }

    public void editProfile(FlipfitCustomer user) {
        System.out.println("Edited User Profile: " + user.getName() + " (Email: " + user.getEmail() + ")");
    }

    public void register(){
        System.out.println("User Registered Successfully");
    }

}
