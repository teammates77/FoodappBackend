package com.foodapp.orderdetails.service;
 
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
 
import com.foodapp.orderdetails.model.Address;
 
@FeignClient(name = "ADDRESS-SERVICE")
public interface AddressService {
 
 
    @GetMapping("/fooddelivery/address/{addressId}")
    public Address getAddress(@PathVariable Integer addressId);
}