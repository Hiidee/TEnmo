package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
    private Long transferID;
    private Long transferTypeID;
    private Long transferStatusID;
    private Long accountFrom;
    private Long accountTo;
    private Double amount;
    private Long userID;
    private String username;
    private Long accountID;

    public Transfer() {
    }

    public Transfer(Long transferID, Long transferTypeID, Long transferStatusID, Long accountFrom, Long accountTo, Double amount, Long userID, String username, Long accountID) {
        this.transferID = transferID;
        this.transferTypeID = transferTypeID;
        this.transferStatusID = transferStatusID;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
        this.userID = userID;
        this.username = username;
        this.accountID = accountID;
    }

    public Long getTransferID() {
        return transferID;
    }

    public void setTransferID(Long transferID) {
        this.transferID = transferID;
    }

    public Long getTransferTypeID() { return transferTypeID; }

    public void setTransferTypeID(Long transferTypeID) {
        this.transferTypeID = transferTypeID;
    }

    public Long getTransferStatusID() {
        return transferStatusID;
    }

    public void setTransferStatusID(Long transferStatusID) {
        this.transferStatusID = transferStatusID;
    }

    public Long getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Long accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Long getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Long accountTo) {
        this.accountTo = accountTo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getUserID() { return userID; }

    public void setUserID(Long userID) { this.userID = userID; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public Long getAccountID() { return accountID; }

    public void setAccountID(Long accountID) {
        this.accountID = accountID;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "transferID=" + transferID +
                "transferTypeID=" + transferTypeID +
                "transferStatusID=" + transferStatusID +
                ", accountFrom=" + accountFrom +
                ", accountTo=" + accountTo +
                ", amount=" + amount +
                '}';
    }
}
