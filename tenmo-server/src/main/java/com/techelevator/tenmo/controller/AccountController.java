package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.model.Account;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountController {

    private AccountDao accountDao;

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public List<Account> getAllAccounts() {
        return accountDao.getAllAccounts();
    }

    @RequestMapping(value = "/account/{userid}", method = RequestMethod.GET)
    public Account getAccountByUserID(@Valid @PathVariable int userid) {
        return accountDao.findByUserID(userid);
    }

}
