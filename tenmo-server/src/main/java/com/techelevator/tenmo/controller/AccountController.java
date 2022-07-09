package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {

    private AccountDao accountDao;
    private UserDao userDao;

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public List<Account> getAllAccounts() {
        return accountDao.getAllAccounts();
    }

    @RequestMapping(value = "/account/{userid}", method = RequestMethod.GET)
    public Account getAccountByUserID(@Valid @PathVariable int userid) {
        return accountDao.findByUserID(userid);
    }

    @RequestMapping(value = "/account/balance", method = RequestMethod.GET) // For security reasons, don't use {id) or a @PathVariable
    public BigDecimal getBalance(Principal principal) {
        String username = principal.getName();
        Long userId = (userDao.findIdByUsername(username)); // It's not finding a userId for the username I provided (but the userId exists in Postgres)
        return accountDao.getBalance(userId) ;
    }

    @RequestMapping(value = "/account/{userid}", method = RequestMethod.PUT)
    public Account sendBucks(@Valid @PathVariable Long userid) {
        return null;
    }

}
