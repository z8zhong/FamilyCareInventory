package com.example.FamilyCareInventory.controller;
import com.example.FamilyCareInventory.model.*;

import com.example.FamilyCareInventory.model.ResponseBody;
import com.example.FamilyCareInventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBody login(@RequestBody LoginForm loginForm) {
        ResponseBody responseBody = new ResponseBody();
        if (userService.login(loginForm.getUsername(), loginForm.getPassword())) {
            String token = loginForm.getUsername();
            return responseBody.ok().data(token);
        } else {
            return responseBody.error();
        }
    }
}
