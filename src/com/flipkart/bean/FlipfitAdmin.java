package com.flipkart.bean;

public class FlipfitAdmin {
    private int adminId;
    /**
     * Password for the admin account.
     */
    private String password;
    private String name;
    private String phoneNumber;
    private String email;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAdminId() {
        return adminId;
    }


    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void display() {
        System.out.println("Admin Details:");
        System.out.println("------------------------------");
        System.out.println("Name: " + name);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("------------------------------");
    }
}
