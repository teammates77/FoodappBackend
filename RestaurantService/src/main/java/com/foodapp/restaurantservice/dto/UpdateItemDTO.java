package com.foodapp.restaurantservice.dto;

import com.foodapp.restaurantservice.model.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateItemDTO {
	
    private Integer itemId;

    private String itemName;

    private Category category;

    private Double cost;
    
    private String itemImageUrl;
    
    private String description;
    
    private RestaurantInfoDTO restaurant;

}
