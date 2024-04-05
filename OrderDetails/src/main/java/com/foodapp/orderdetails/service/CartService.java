package com.foodapp.orderdetails.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.foodapp.orderdetails.model.FoodCart;

@FeignClient(name = "FOODCART-SERVICE")
public interface CartService {

    @GetMapping("/fooddelivery/foodcart/{cartId}")
    public FoodCart getCart(@PathVariable Integer cartId);

}
