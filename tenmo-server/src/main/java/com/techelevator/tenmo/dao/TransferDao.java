package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

    boolean createTransfer(Long userIDOfSender, Transfer transfer);

    Transfer getTransferDetails(long transferID);

    List<Transfer> getTransferHistory(long userID);

}

