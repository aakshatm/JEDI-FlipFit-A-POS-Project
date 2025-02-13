package com.flipkart.business;

import com.flipkart.bean.FlipfitUser;

public class FlipfitUserService {
    public void login(FlipfitUser user) {
        System.out.println(user.getName() + " logged in successfully!");
    }

    public void logout(FlipfitUser user) {
        System.out.println(user.getName() + " logged out.");
    }

    public void viewProfile(FlipfitUser user) {
        System.out.println("User Profile: " + user.getName() + " (Email: " + user.getEmail() + ")");
    }

    public void editProfile(FlipfitUser user) {
        System.out.println("Edited User Profile: " + user.getName() + " (Email: " + user.getEmail() + ")");
    }

    public void register(){
        System.out.println("User Registered Successfully");
    }
}
