package com.flipkart.bean;

public class Notification {
    /**
     * Category of the notification (e.g., "Alert", "Reminder").
     */
    private String category;

    /**
     * Message content of the notification.
     */
    private String message;

    /**
     * Unique identifier for the notification.
     */
    private int notificationId;

    /**
     * Gets the unique identifier for the notification.
     *
     * @return the notificationId, which is the unique identifier for the notification.
     */
    public int getNotificationId() {
        return notificationId;
    }

    /**
     * Sets the unique identifier for the notification.
     *
     * @param notificationId the notificationId to set, which is the unique identifier for the notification.
     */
    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    /**
     * Gets the category of the notification.
     *
     * @return the category of the notification (e.g., "Alert", "Reminder").
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of the notification.
     *
     * @param category the category to set for the notification (e.g., "Alert", "Reminder").
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets the message content of the notification.
     *
     * @return the message content of the notification.
     */
    public String getMessage() {
        return message;
    }


    /**
     * Sets the message for the notification.
     *
     * @param message The message to be set for the notification.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
