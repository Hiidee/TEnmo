package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public List<Transfer> getTransferHistory(@PathVariable Long userid) {return transferDao.getTransferHistory(userid);}

    @RequestMapping(value = "/transfer/{transferid}", method = RequestMethod.GET)
    public Transfer getTransferDetails(@Valid @PathVariable Long transferid) {
        return transferDao.getTransferDetails(transferid);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/transfer", method = RequestMethod.PUT)
    public boolean createTransfer(@Valid @RequestBody Transfer transfer, Principal principal) {
        Long userIDOfSender = userDao.findIdByUsername(principal.getName());
        return transferDao.createTransfer(userIDOfSender, transfer);
    }
}
