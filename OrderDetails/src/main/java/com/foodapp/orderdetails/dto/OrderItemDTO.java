package com.foodapp.orderdetails.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Integer itemId;
    private String itemName;
    private Integer quantity;
    private Double cost;
    private Integer userId;
    private Integer restaurantId;
}
