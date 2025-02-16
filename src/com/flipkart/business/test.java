package com.flipkart.business;

public class test {
    public static void main(String[] args) {
        FlipfitAdminInterface adminService = new FlipfitAdminService();
        System.out.println(adminService.login(101, "Election77@7"));
        adminService.editProfile(101, "random");

    }
}
