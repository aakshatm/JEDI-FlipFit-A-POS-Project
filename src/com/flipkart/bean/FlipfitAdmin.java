package com.flipkart.bean;

/**
 * Represents an administrator in the FlipFit system.
 * The admin has an ID, password, name, phone number, and email.
 */
public class FlipfitAdmin {
    private int adminId;

    /**
     * Password for the admin account.
     */
    private String password;
    private String name;
    private String phoneNumber;
    private String email;

    /**
     * Gets the name of the admin.
     *
     * @return The admin's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the admin.
     *
     * @param name The admin's name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the phone number of the admin.
     *
     * @return The admin's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the admin.
     *
     * @param phoneNumber The admin's phone number to be set.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the email of the admin.
     *
     * @return The admin's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the admin.
     *
     * @param email The admin's email address to be set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the admin ID.
     *
     * @return The unique ID of the admin.
     */
    public int getAdminId() {
        return adminId;
    }

    /**
     * Sets the admin ID.
     *
     * @param adminId The unique ID of the admin to be set.
     */
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    /**
     * Gets the admin password.
     *
     * @return The admin's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the admin password.
     *
     * @param password The password to be set for the admin.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Displays the details of the admin, including name, phone number, and email.
     */
    public void display() {
        System.out.println("Admin Details:");
        System.out.println("------------------------------");
        System.out.println("Name: " + name);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("------------------------------");
    }
}
