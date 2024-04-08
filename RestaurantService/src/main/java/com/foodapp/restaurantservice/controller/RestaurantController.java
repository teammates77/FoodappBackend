package com.foodapp.restaurantservice.controller;

import feign.FeignException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.foodapp.restaurantservice.dto.MerchantRestaurantDTO;
import com.foodapp.restaurantservice.dto.RestaurantDTO;
import com.foodapp.restaurantservice.dto.RestaurantInfoDTO;
import com.foodapp.restaurantservice.dto.RestaurantsInItemDTO;
import com.foodapp.restaurantservice.model.Address;
import com.foodapp.restaurantservice.model.Restaurant;
import com.foodapp.restaurantservice.service.AddressService;
import com.foodapp.restaurantservice.service.RestaurantService;

import java.util.List;

@RestController

@RequestMapping("/fooddelivery/restaurant")
public class RestaurantController {

    
    private final RestaurantService restaurantService;
    private final AddressService addressService; 
    
    @Autowired
    public RestaurantController(RestaurantService restaurantService,AddressService addressService) {
    	
    	this.restaurantService=restaurantService;
    	this.addressService=addressService;
    }

    @PostMapping("/add")
    public ResponseEntity<RestaurantsInItemDTO> addRestaurant(@RequestBody RestaurantsInItemDTO restaurant){

        RestaurantsInItemDTO registeredRestaurant = restaurantService.addRestaurant(restaurant);

        return new ResponseEntity<>(registeredRestaurant, HttpStatus.CREATED);

    }

    
    @PutMapping("/update")
    public ResponseEntity<MerchantRestaurantDTO> updateRestaurant(@RequestBody MerchantRestaurantDTO restaurantDTO){

        // Call the service method directly with the DTO object
    	MerchantRestaurantDTO updatedRestaurant = restaurantService.updateRestaurant(restaurantDTO);

        return new ResponseEntity<>(updatedRestaurant, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/{restaurantId}")

    public ResponseEntity<Restaurant> removeRestaurant(@PathVariable Integer restaurantId){

        Restaurant removedRestaurant = restaurantService.removeRestaurant(restaurantId);

        return new ResponseEntity<>(removedRestaurant,HttpStatus.OK);

    }
    
    @GetMapping("/merchant/{merchantId}")
    public ResponseEntity<MerchantRestaurantDTO> viewRestaurantByMerchantId(@PathVariable Integer merchantId) {
        MerchantRestaurantDTO restaurantDTO = restaurantService.getRestaurantByMerchantId(merchantId);
        return ResponseEntity.ok(restaurantDTO);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDTO> viewRestaurant(@PathVariable Integer restaurantId){

        RestaurantDTO restaurant = restaurantService.viewRestaurant(restaurantId);

        return new ResponseEntity<>(restaurant,HttpStatus.OK);

    }
    
    @GetMapping("/all")
    public ResponseEntity<List<RestaurantInfoDTO>> getAllRestaurants() {
        List<RestaurantInfoDTO> restaurants = restaurantService.getAllRestaurants();
        if (restaurants.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(restaurants, HttpStatus.OK);
        }
    }


}
