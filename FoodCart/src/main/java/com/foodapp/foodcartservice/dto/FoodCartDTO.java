package com.foodapp.foodcartservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import com.foodapp.foodcartservice.model.CartItem;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodCartDTO {

    private Integer cartId;

    private Integer userId;

    private List<CartItem> items = new ArrayList<>();

}
