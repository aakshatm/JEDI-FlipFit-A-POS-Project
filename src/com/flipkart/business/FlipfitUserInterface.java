package com.flipkart.business;

import com.flipkart.bean.FlipfitUser;

import java.util.HashSet;
import java.util.Set;

public interface FlipfitUserInterface {
    void login(FlipfitUser user);
    void logout(FlipfitUser user);
    void viewProfile(FlipfitUser user);
    void editProfile(FlipfitUser user);
    void register();
}
