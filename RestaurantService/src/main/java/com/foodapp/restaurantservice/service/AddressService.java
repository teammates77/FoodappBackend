package com.foodapp.restaurantservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.foodapp.restaurantservice.model.Address;

@FeignClient(name = "ADDRESS-SERVICE")
public interface AddressService {

    @PostMapping("/fooddelivery/address")
    public Address saveAddress(@RequestBody Address address);

    @GetMapping("/fooddelivery/address/{addressId}")
    public Address getAddress(@PathVariable Integer addressId);

    @DeleteMapping("/fooddelivery/address/{addressId}")
    public Address deleteAddress(@PathVariable Integer addressId);

}
