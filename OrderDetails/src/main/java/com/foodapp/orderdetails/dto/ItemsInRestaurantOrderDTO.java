package com.foodapp.orderdetails.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemsInRestaurantOrderDTO{

    private Integer itemId;

    private String itemName;

    private Integer quantity;

    private Double cost;
    
    private Integer restaurantId;

}