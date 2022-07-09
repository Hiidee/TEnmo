package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JdbcTransferDao implements TransferDao {
    @Override
    public Transfer getTransferDetailsByTransferId(long transferID) {
    List<Transfer> transfers = new ArrayList<>();
        return null;
    }

    @Override
    public List<Transfer> viewAllTransfersById(long userID) {
        //String sql = "SELECT "
        return null;
    }

    @Override
    public Transfer updateSender(long userID, long transferTypeID, BigDecimal amount) {
        return null;
    }

    @Override
    public Transfer updateRecipient(long userID, long transferTypeId, BigDecimal amount) {
        return null;
    }

}
