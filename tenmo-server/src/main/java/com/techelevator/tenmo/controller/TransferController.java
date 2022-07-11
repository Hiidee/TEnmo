package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TransferController {

    private TransferDao transferDao;

    public TransferController(TransferDao transferDao) {
        this.transferDao = transferDao;
    }

    // Business logic - only allow a transfer if:
    // The sender has enough money in their account to make the transfer (currentBalance >= amountToSend)
    // The sender is trying to send a positive amount of money (amountToSend > 0)


    //@RequestMapping(value = "/transfers", method = RequestMethod.GET)
//    public List<Transfer> viewTransferHistory() {return transferDao.getTransferHistory();}
//
//    @RequestMapping(value = "/transfers/{transferid}", method = RequestMethod.GET)
//    public Transfer getTransferDetailsByTransferId(@Valid @PathVariable Long transferid) {
//        return transferDao.getTransferDetails(transferid);
//    }
}
