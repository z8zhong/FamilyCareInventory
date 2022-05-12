package com.example.FamilyCareInventory.controller;

import com.example.FamilyCareInventory.model.*;
import com.example.FamilyCareInventory.model.ResponseBody;
import com.example.FamilyCareInventory.service.ProductService;
import com.example.FamilyCareInventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @RequestMapping(value = "product", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBody listProduct(@RequestParam String token, @RequestBody Product product) throws UnsupportedEncodingException {
        token = URLDecoder.decode(token, "utf-8");
        ResponseBody responseBody = new ResponseBody();
        String role = userService.getRoleByUsername(token);
        if (role.equals("admin")) {
            int productId = productService.listProduct(product);
            return responseBody.ok().data(productId);
        } else {
            return responseBody.notAuthorized();
        }
    }

    @RequestMapping(value = "product", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBody getAllProduct(@RequestParam String token) throws UnsupportedEncodingException {
        token = URLDecoder.decode(token, "utf-8");
        ResponseBody responseBody = new ResponseBody();
        String role = userService.getRoleByUsername(token);
        if (role.equals("admin")) {
            List<Product> productList = productService.getAllProduct();
            return responseBody.ok().data(productList);
        } else {
            return responseBody.notAuthorized();
        }
    }

    @RequestMapping(value = "product", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBody deleteProduct(@RequestParam String token, int productId) throws UnsupportedEncodingException {
        token = URLDecoder.decode(token, "utf-8");
        ResponseBody responseBody = new ResponseBody();
        String role = userService.getRoleByUsername(token);
        if (role.equals("admin")) {
            productService.deleteProduct(productId);
            return responseBody.ok();
        } else {
            return responseBody.notAuthorized();
        }
    }
}
