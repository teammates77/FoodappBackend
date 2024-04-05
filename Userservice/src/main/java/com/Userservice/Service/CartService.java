package com.Userservice.Service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import com.Userservice.DTO.FoodCartDTO;
@Service
@FeignClient(name = "FOODCART-SERVICE")
public interface CartService {

    @PostMapping("/fooddelivery/foodcart")
    public FoodCartDTO saveFoodCart(@RequestBody FoodCartDTO foodCartDTO);

    @DeleteMapping("/fooddelivery/foodcart/{cartId}")
    public FoodCartDTO removeCart(@PathVariable Integer cartId);

}