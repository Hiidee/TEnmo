package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {
    private int userID;
    private int accountID;
    private BigDecimal amount;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "\n--------------------------------------------" +
                "\n Transfer Details" +
                "\n--------------------------------------------" +
                "\n User ID: " + userID +
                "\n Account ID: " + accountID +
                "\n Amount: " + amount;
    }
}
