package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    List<User> findAllNotMe(String username);

    User findByUsername(String username);

    Long findIdByUsername(String username);

    boolean create(String username, String password);
}
