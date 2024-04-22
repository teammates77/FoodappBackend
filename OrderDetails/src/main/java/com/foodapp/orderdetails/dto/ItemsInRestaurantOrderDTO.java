package com.foodapp.orderdetails.dto;

import com.foodapp.orderdetails.model.Address;

import jakarta.persistence.Embedded;
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
    private String deliveryStatus;
    private Integer orderItemId;
    
    private Integer restaurantId;
    @Embedded
    private Address address;
 
		
	}

