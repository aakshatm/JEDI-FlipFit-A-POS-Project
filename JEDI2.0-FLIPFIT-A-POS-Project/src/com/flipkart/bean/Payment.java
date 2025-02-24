package com.flipkart.bean;

public class Payment {
    /**
     * Unique identifier for the payment transaction.
     */
    private int paymentId;

    /**
     * Credit or debit card number used for the payment.
     */
    private String cardNumber;

    /**
     * Expiry date of the card in the format MM/YY.
     */
    private String expiryDate;

    /**
     * Name of the cardholder as printed on the card.
     */
    private String name;

    /**
     * CVV (Card Verification Value) of the card.
     */
    private String cvv;

    /**
     * Gets the unique identifier for the payment transaction.
     *
     * @return the paymentsId, which is the unique identifier for the payment.
     */
    public int getPaymentId() {
        return paymentId;
    }

    /**
     * Sets the unique identifier for the payment transaction.
     *
     * @param paymentId the paymentsId to set, which is the unique identifier for the payment.
     */
    public void setPaymentsId(int paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * Gets the credit or debit card number used for the payment.
     *
     * @return the cardNumber, which is the card number used for the payment.
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the credit or debit card number used for the payment.
     *
     * @param cardNumber the cardNumber to set, which is the card number used for the payment.
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Gets the expiry date of the card.
     *
     * @return the expiryDate of the card in the format MM/YY.
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiry date of the card.
     *
     * @param expiryDate the expiryDate to set, in the format MM/YY.
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Gets the name of the cardholder as printed on the card.
     *
     * @return the name of the cardholder.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the cardholder as printed on the card.
     *
     * @param name the name to set, which is the cardholder's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the CVV (Card Verification Value) of the card.
     *
     * @return the cvv of the card.
     */
    public String getCvv() {
        return cvv;
    }

    /**
     * Sets the CVV (Card Verification Value) of the card.
     *
     * @param cvv the cvv to set, which is the card verification value.
     */
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
