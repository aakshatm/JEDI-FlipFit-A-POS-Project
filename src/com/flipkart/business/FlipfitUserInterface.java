package com.flipkart.business;

public interface FlipfitUserInterface {
    void login(FlipfitUser user);
    void logout(FlipfitUser user);
    void viewProfile(FlipfitUser user);
    void editProfile(FlipfitUser user);
    void register();
}
