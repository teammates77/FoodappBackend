package com.foodapp.restaurantservice.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import com.foodapp.restaurantservice.model.Address;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {

    private Integer restaurantId;

    private String restaurantName;

    @Embedded
    private Address address;

    private List<ItemsInRestaurantDTO> items = new ArrayList<>();
    
    private String restaurant_image_Url;

    private String managerName;

    private String contact;
}
