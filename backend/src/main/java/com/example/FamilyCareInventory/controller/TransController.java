package com.example.FamilyCareInventory.controller;

import com.example.FamilyCareInventory.model.*;
import com.example.FamilyCareInventory.model.ResponseBody;
import com.example.FamilyCareInventory.service.BuyService;
import com.example.FamilyCareInventory.service.TransService;
import com.example.FamilyCareInventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;


@RestController
public class TransController {

    private final TransService transService;
    private final UserService userService;

    @Autowired
    public TransController(TransService transService, UserService userService) {
        this.transService = transService;
        this.userService = userService;
    }

    @RequestMapping(value = "trans", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBody getAllTrans(@RequestParam String token) throws UnsupportedEncodingException {
        token = URLDecoder.decode(token, "utf-8");
        ResponseBody responseBody = new ResponseBody();
        String role = userService.getRoleByUsername(token);
        if (role.equals("admin")) {
            ArrayList<Trans> transItem = transService.getAllTrans();
            return responseBody.ok().data(transItem);
        } else {
            return responseBody.notAuthorized();
        }
    }

    @RequestMapping(value = "transsum", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBody getAccountSummary(@RequestParam String token) throws UnsupportedEncodingException {
        token = URLDecoder.decode(token, "utf-8");
        ResponseBody responseBody = new ResponseBody();
        String role = userService.getRoleByUsername(token);
        if (role.equals("admin")) {
            ArrayList<AccountSummary> accountSummarys = transService.getAccountSummary();
            return responseBody.ok().data(accountSummarys);
        } else {
            return responseBody.notAuthorized();
        }
    }
}
