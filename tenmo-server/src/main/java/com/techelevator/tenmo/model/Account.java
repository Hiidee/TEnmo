package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {
    private long accountID;
    private long userID;
    private BigDecimal balance;

    public Account() {

    }

    public Account(Long accountID, long userID, BigDecimal balance) {
        this.accountID = accountID;
        this.userID = userID;
        this.balance = balance;
    }

    public long getAccountID() {
        return accountID;
    }

    public void setAccountID(long accountID) {
        this.accountID = accountID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_id: '" + accountID +
                ", user_id: " + userID + '\'' +
                ", balance: " + balance +
                '}';
    }
}
