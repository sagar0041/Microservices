package com.common.retail.shoppingcartservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartRestController {

    @RequestMapping("/shopping")
    public String welcome() {
        return "Welcome to shopping cart";
    }

    @RequestMapping("/shopping/cart")
    public String Cart() {
        return "Welcome to cart";
    }
}
