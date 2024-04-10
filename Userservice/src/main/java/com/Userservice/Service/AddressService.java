
package com.Userservice.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import com.Userservice.model.Address;

@Service
@FeignClient(name = "ADDRESS-SERVICE")
public interface AddressService {

    @PostMapping("/fooddelivery/address")
    public Address saveAddress(@RequestBody Address address);

    @GetMapping("/fooddelivery/address/{addressId}")
    public Address getAddress(@PathVariable Integer addressId);

    @DeleteMapping("/fooddelivery/address/{addressId}")
    public Address deleteAddress(@PathVariable Integer addressId);
    
    @PutMapping("/fooddelivery/address")
    public Address updateAddress(@RequestBody Address address); // Define the updateAddress method here
}

