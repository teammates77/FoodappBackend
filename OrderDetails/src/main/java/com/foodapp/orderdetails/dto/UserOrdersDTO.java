package com.foodapp.orderdetails.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@AllArgsConstructor
public class UserOrdersDTO {
    private Integer itemId;
    private String itemName;
    private Integer quantity;
    private Double cost;
    private String status;
    private Integer userid;
}