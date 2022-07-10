package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

    // Send (post) money(a specific amount)
    // Receive money - This doesn't need to be accounted for as an actual method, correct?
    // Update (put) the recipient's balance (increase by the amount received) - Do this in the controller
    // Update (put) the sender's balance (decrease by the amount sent) - Do this in the controller
    // View (get) my own transfer history
    // View (get) the details of a transfer based on a transfer id

    Transfer getTransferDetailsByTransferId(long transferID);

    List<Transfer> viewTransferHistory(long userID); // This is a user's transfer history - what parameters should be passed in here?

    Transfer updateSender(long userID, long transferTypeID, BigDecimal amount); // Can I include userID here since it's from a different table?

    Transfer updateRecipient(long userID, long transferTypeId, BigDecimal amount); // Can I include userID here since it's from a different table?

    //Transfer create(long transferID, )

    // Transfer update()
}

