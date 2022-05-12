package com.example.FamilyCareInventory.controller;

import com.example.FamilyCareInventory.model.*;
import com.example.FamilyCareInventory.model.ResponseBody;
import com.example.FamilyCareInventory.service.BuyService;
import com.example.FamilyCareInventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
public class BuyController {

    private final BuyService buyService;
    private final UserService userService;

    @Autowired
    public BuyController(BuyService buyService, UserService userService) {
        this.buyService = buyService;
        this.userService = userService;
    }

    @RequestMapping(value = "buy", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBody addBuy(@RequestParam String token, @RequestBody Buy buy) throws UnsupportedEncodingException {
        token = URLDecoder.decode(token, "utf-8");
        ResponseBody responseBody = new ResponseBody();
        String role = userService.getRoleByUsername(token);
        if (role.equals("admin")) {
            int buyId = buyService.addBuy(buy);
            return responseBody.ok().data(buyId);
        } else {
            return responseBody.notAuthorized();
        }
    }

    @RequestMapping(value = "buy", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBody getAllBuy(@RequestParam String token) throws UnsupportedEncodingException {
        token = URLDecoder.decode(token, "utf-8");
        ResponseBody responseBody = new ResponseBody();
        String role = userService.getRoleByUsername(token);
        if (role.equals("admin")) {
            ArrayList<Buy> buys = buyService.getAllBuy();
            return responseBody.ok().data(buys);
        } else {
            return responseBody.notAuthorized();
        }
    }

    @RequestMapping(value = "buyline", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBody getBuyLine(@RequestParam String token, Integer buyId) throws UnsupportedEncodingException {
        token = URLDecoder.decode(token, "utf-8");
        ResponseBody responseBody = new ResponseBody();
        String role = userService.getRoleByUsername(token);
        if (role.equals("admin")) {
            ArrayList<BuyLine> buylines = buyService.getBuyLine(buyId);
            return responseBody.ok().data(buylines);
        } else {
            return responseBody.notAuthorized();
        }
    }

    @RequestMapping(value = "buy", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBody deleteBuy(@RequestParam String token, int buyId) throws UnsupportedEncodingException {
        token = URLDecoder.decode(token, "utf-8");
        ResponseBody responseBody = new ResponseBody();
        String role = userService.getRoleByUsername(token);
        if (role.equals("admin")) {
            buyService.deleteBuy(buyId);
            return responseBody.ok();
        } else {
            return responseBody.notAuthorized();
        }
    }

    @RequestMapping(value = "buy", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBody updateShipTime(@RequestBody ShipTimeUpdateForm shipTimeUpdateForm) throws UnsupportedEncodingException {
        String token = shipTimeUpdateForm.getToken();
        Date ship_time = shipTimeUpdateForm.getShip_time();
        int buyId = shipTimeUpdateForm.getBuyId();
        token = URLDecoder.decode(token, "utf-8");
        ResponseBody responseBody = new ResponseBody();
        String role = userService.getRoleByUsername(token);
        if (role.equals("admin")) {
            buyService.updateShipTime(ship_time, buyId);
            return responseBody.ok();
        } else {
            return responseBody.notAuthorized();
        }
    }
}
