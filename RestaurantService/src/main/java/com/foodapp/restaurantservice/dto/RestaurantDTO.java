package com.foodapp.restaurantservice.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {

    private Integer restaurantId;
    private Integer merchantId;
    private String restaurantName;

    private List<ItemsInRestaurantDTO> items = new ArrayList<>();
    
    private String restaurant_image_Url;

    private String managerName;

    private String contact;
    
    private String addressLine;

    private String city;

    private String state;

    private String country;

    private Integer pinCode;
}



