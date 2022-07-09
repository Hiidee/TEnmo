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

//    @RequestMapping(value = "/transfers", method = RequestMethod.GET)
//    public List<Transfer> getTransferHistory() {return transferDao.getTransferHistory();}
//
//    @RequestMapping(value = "/transfers/{transferid}", method = RequestMethod.GET)
//    public Transfer getTransferDetails(@Valid @PathVariable Long transferid) {
//        return transferDao.getTransferDetails(transferid);
//    }
}
