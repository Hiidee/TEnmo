package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {

    private AccountDao accountDao;
    private UserDao userDao;

    public AccountController(AccountDao accountDao, UserDao userDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public List<Account> getAllAccounts() {
        return accountDao.getAllAccounts();
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET) //use this to get the account_id of a given user based on the send recipient and current user id's
    public Account getAccountByUserID(@Valid @PathVariable int userid) {
        return accountDao.findByUserID(userid);
    }

    @RequestMapping(value = "/account/balance", method = RequestMethod.GET) // For security reasons, don't use {id} or a @PathVariable
    public BigDecimal getBalance(Principal principal) {
        String username = principal.getName();
        Long userId = (userDao.findIdByUsername(username));
        return accountDao.getBalance(userId);
    }

    @RequestMapping(value = "/account/{userid}", method = RequestMethod.PUT)
    public Account sendBucks(@Valid @PathVariable Long userid) {
        return null;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

}
