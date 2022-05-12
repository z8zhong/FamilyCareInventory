package com.example.FamilyCareInventory.controller;

import com.example.FamilyCareInventory.model.Buy;
import com.example.FamilyCareInventory.model.Cost;
import com.example.FamilyCareInventory.model.Meta;
import com.example.FamilyCareInventory.model.ResponseBody;
import com.example.FamilyCareInventory.service.CostService;
import com.example.FamilyCareInventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

@RestController
public class CostController {
    CostService costService;
    UserService userService;

    @Autowired
    public CostController(CostService costService, UserService userService) {
        this.costService = costService;
        this.userService = userService;
    }

    @RequestMapping(value = "cost", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBody addCost(@RequestParam String token, @RequestBody Cost cost) throws UnsupportedEncodingException {
        token = URLDecoder.decode(token, "utf-8");
        ResponseBody responseBody = new ResponseBody();
        String role = userService.getRoleByUsername(token);
        if (role.equals("admin")) {
            int buyId = costService.addCost(cost);
            return responseBody.ok().data(buyId);
        } else {
            return responseBody.notAuthorized();
        }
    }

    @RequestMapping(value = "cost", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBody getAllCost(@RequestParam String token) throws UnsupportedEncodingException {
        token = URLDecoder.decode(token, "utf-8");
        ResponseBody responseBody = new ResponseBody();
        String role = userService.getRoleByUsername(token);
        if (role.equals("admin")) {
            ArrayList<Cost> costs = costService.getAllCost();
            return responseBody.ok().data(costs);
        } else {
            return responseBody.notAuthorized();
        }
    }

    @RequestMapping(value = "cost", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBody deleteCost(@RequestParam String token, int costId) throws UnsupportedEncodingException {
        token = URLDecoder.decode(token, "utf-8");
        ResponseBody responseBody = new ResponseBody();
        String role = userService.getRoleByUsername(token);
        if (role.equals("admin")) {
            costService.deleteCost(costId);
            return responseBody.ok();
        } else {
            return responseBody.notAuthorized();
        }
    }
}
