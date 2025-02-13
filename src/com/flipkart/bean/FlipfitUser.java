package com.flipkart.bean;

public class FlipfitUser {
    private int userId;
    private String email;
    private String name;
    private String password;
    private String contactNumber;

    public FlipfitUser() {

    }

    public FlipfitUser(int userId, String email, String name, String password, String contactNumber) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.contactNumber = contactNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
