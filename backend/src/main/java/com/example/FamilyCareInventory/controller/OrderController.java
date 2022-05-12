package com.example.FamilyCareInventory.controller;

import com.example.FamilyCareInventory.model.ResponseBody;
import com.example.FamilyCareInventory.model.*;
import com.example.FamilyCareInventory.service.OrderService;
import com.example.FamilyCareInventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;


@RestController
public class OrderController {

    private final OrderService OrderService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService OrderService, UserService userService) {
        this.OrderService = OrderService;
        this.userService = userService;
    }

    @RequestMapping(value = "order", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBody addOrder(@RequestParam String token, @RequestBody Order order) throws UnsupportedEncodingException {
        token = URLDecoder.decode(token, "utf-8");
        ResponseBody responseBody = new ResponseBody();
        String role = userService.getRoleByUsername(token);
        if (role.equals("admin")) {
            order.setEmployee_name(token);
             int orderId = OrderService.addOrder(order);
            return responseBody.ok().data(orderId);
        } else {
            return responseBody.notAuthorized();
        }
    }

    @RequestMapping(value = "order", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBody getAllOrder(@RequestParam String token) throws UnsupportedEncodingException {
        token = URLDecoder.decode(token, "utf-8");
        String role = userService.getRoleByUsername(token);
        ResponseBody responseBody = new ResponseBody();
        if (role.equals("admin")) {
            ArrayList<Order> orders = OrderService.getAllOrder();
            return responseBody.ok().data(orders);
        } else {
            return responseBody.notAuthorized();
        }
    }

    @RequestMapping(value = "orderline", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBody getOrderLine(@RequestParam String token, Integer orderId) throws UnsupportedEncodingException {
        token = URLDecoder.decode(token, "utf-8");
        ResponseBody responseBody = new ResponseBody();
        String role = userService.getRoleByUsername(token);
        if (role.equals("admin")) {
            ArrayList<OrderLine> orderLines = OrderService.getOrderLine(orderId);
            return responseBody.ok().data(orderLines);
        } else {
            return responseBody.notAuthorized();
        }
    }

    @RequestMapping(value = "order", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBody deleteOrder(@RequestParam String token, int orderId) throws UnsupportedEncodingException {
        token = URLDecoder.decode(token, "utf-8");
        ResponseBody responseBody = new ResponseBody();
        String role = userService.getRoleByUsername(token);
        if (role.equals("admin")) {
            OrderService.deleteOrder(orderId);
            return responseBody.ok();
        } else {
            return responseBody.notAuthorized();
        }
    }
}
