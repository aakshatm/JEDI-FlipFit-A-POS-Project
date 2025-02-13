package com.flipkart.business;

import com.flipkart.bean.FlipfitUser;

public interface FlipfitUserInterface {
    void login(FlipfitUser user);
    void logout(FlipfitUser user);
    void viewProfile(FlipfitUser user);
    void editProfile(FlipfitUser user);
    void register();
}
