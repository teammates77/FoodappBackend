package com.foodapp.categoryservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodCart {

    private Integer cartId;

    private User user;

    private List<Item> items = new ArrayList<>();

}
