package com.flipkart.business;

import com.flipkart.bean.User;

public class FlipfitUserService {
    public void login(User user) {
        System.out.println(user.getName() + " logged in successfully!");
    }

    public void logout(User user) {
        System.out.println(user.getName() + " logged out.");
    }

    public void viewProfile(User user) {
        System.out.println("User Profile: " + user.getName() + " (Email: " + user.getEmail() + ")");
    }

    public void editProfile(User user) {
        System.out.println("Edited User Profile: " + user.getName() + " (Email: " + user.getEmail() + ")");
    }

    public void register(){
        System.out.println("User Registered Successfully");
    }
}
