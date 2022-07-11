package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

    boolean createTransfer(Transfer transfer);

    Transfer getTransferDetails(long transferID);

    List<Transfer> viewTransferHistory(long userID);

}

