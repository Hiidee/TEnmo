package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TransferController {

    private TransferDao transferDao;

    @RequestMapping(value = "/transfer", method = RequestMethod.GET)
    public List<Transfer> viewTransferHistory(@Valid @PathVariable Long userID) {
        return transferDao.viewTransferHistory(userID);
    }

    @RequestMapping(value = "/transfer/{transferid}", method = RequestMethod.GET)
    public Transfer getTransferDetails(@Valid @PathVariable Long transferid) {
        return transferDao.getTransferDetails(transferid);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public Transfer createTransfer(@Valid @RequestBody Transfer transfer) {
        transferDao.createTransfer(transfer);
        return transfer;
    }
}
