package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class TransferController {

    private TransferDao transferDao;
    private UserDao userDao;

    public TransferController(TransferDao transferDao, UserDao userDao) {
        this.transferDao = transferDao;
        this.userDao = userDao;
    }

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
    public boolean createTransfer(@Valid @RequestBody Transfer transfer, Principal principal) {
        Long currentUser = userDao.findIdByUsername(principal.getName());
        return transferDao.createTransfer(currentUser, transfer);
    }
}
