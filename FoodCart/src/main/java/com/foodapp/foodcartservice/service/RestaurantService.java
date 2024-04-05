package com.foodapp.foodcartservice.service;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.foodapp.foodcartservice.model.Restaurant;


@FeignClient(name = "RESTAURANT-SERVICE")
public interface RestaurantService {

    @GetMapping("/fooddelivery/restaurant/{restaurantId}")
    public Restaurant getRestaurant(@PathVariable Integer restaurantId);
    
//    @GetMapping("/fooddelivery/items/itembyrestaurant/{restaurantId}")
//    public <List<ItemsInRestaurantDTO>> viewItemByRestaurant(@PathVariable Integer restaurantId);

}