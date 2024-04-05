package com.foodapp.orderdetails.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Item {
    private Integer itemId;

    private String itemName;

    private Integer quantity;

    private Double cost;
    
   // private String status;
    
    private Integer restaurantId;
}
