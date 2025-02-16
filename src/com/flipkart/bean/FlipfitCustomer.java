package com.flipkart.bean;

import java.util.ArrayList;
import java.util.List;

public class FlipfitCustomer {
    /**
     * Unique identifier for the customer.
     */
    private int customerId;

    /**
     * Name of the user.
     */
    private String userName;

    /**
     * Phone number of the user.
     */
    private String phoneNumber;

    /**
     * Address of the user.
     */
    private String address;

    /**
     * Location of the user.
     */
    private String location;

    /**
     * Email address of the user.
     */
    private String email;

    /**
     * Password for the user's account.
     */
    private String password;

    /**
     * Gets the unique identifier for the user.
     *
     * @return the userId, which is the unique identifier for the user.
     */
    public int getUserId() {
        return customerId;
    }

    /**
     * Sets the unique identifier for the user.
     *
     * @param customerId the userId to set, which is the unique identifier for the user.
     */
    public void setUserId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the name of the user.
     *
     * @return the userName of the user.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the name of the user.
     *
     * @param userName the userName to set for the user.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the email address of the user.
     *
     * @return the email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email the email address to set for the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the phone number of the user.
     *
     * @return the phoneNumber of the user.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the user.
     *
     * @param phoneNumber the phoneNumber to set for the user.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the address of the user.
     *
     * @return the address of the user.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the user.
     *
     * @param address the address to set for the user.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the location of the user.
     *
     * @return the location of the user.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the user.
     *
     * @param location the location to set for the user.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the password for the user's account.
     *
     * @return the password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the user's account.
     *
     * @param password the password to set for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public void displayCustomer() {
        System.out.println("Customer Details:");
        System.out.println("------------------------------");
        System.out.println("Customer ID: " + getUserId());
        System.out.println("Name: " + getUserName());
        System.out.println("Email: " + getEmail());
        System.out.println("Phone Number: " + getPhoneNumber());
        System.out.println("Address: " + getAddress());
        System.out.println("Location: " + getLocation());
        System.out.println("------------------------------");
    }
}
