package com.example.FamilyCareInventory.service;

import com.example.FamilyCareInventory.dao.UserJdbcTemp;
import com.example.FamilyCareInventory.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserJdbcTemp userJdbcTemp;

    @Autowired
    public UserService(UserJdbcTemp userJdbcTemp) {
        this.userJdbcTemp = userJdbcTemp;
    }

    public User getUser(int username) {
        return userJdbcTemp.getUser(username);
    }

    public boolean login(String username, String password) {
        return userJdbcTemp.validateUserByUsername(username, password);
    }

    public String getRoleByUsername(String username) {
        return userJdbcTemp.getRoleByUsername(username);
    }
}
