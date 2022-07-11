package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private UserDao userDao;

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public User findByUsername(@Valid @PathVariable String username) {
        return userDao.findByUsername(username);
    }

    @RequestMapping(value = "/user/{idbyusername}", method = RequestMethod.GET)
    public Long findIdByUsername(@Valid @PathVariable String idbyusername) {
        return userDao.findIdByUsername(idbyusername);
    }
}
