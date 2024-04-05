package com.foodapp.foodcartservice.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.foodapp.foodcartservice.model.Category;
import com.foodapp.foodcartservice.model.FoodCart;
import com.foodapp.foodcartservice.model.Restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private Integer cartItemId;

    private Integer itemId;

    private String itemName;

    private Category category;

    private Integer quantity;

    private Double cost;

    private Restaurant restaurant;

    @JsonIgnore
    private FoodCart foodCart ;

}
