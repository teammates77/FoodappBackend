package com.foodapp.restaurantservice.dto;

import com.foodapp.restaurantservice.model.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateItemRequestDTO {
    private Integer itemId;
    private String itemName;
    private String description;
    private String itemimageUrl;
    private Double cost;
    private Integer restaurantId;
    private Integer categoryId;
}