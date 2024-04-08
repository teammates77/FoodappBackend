package com.foodapp.restaurantservice.dto;


import com.foodapp.restaurantservice.model.Category;
import com.foodapp.restaurantservice.model.Restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemsInRestaurantDTO {

    private Integer itemId;

    private String itemName;

    private Category category;

    private Double cost;
    
    private String ItemimageUrl;
    
    private String description;
    
   // private RestaurantInfoDTO restaurant;
    //private Restaurant restaurant;

}
