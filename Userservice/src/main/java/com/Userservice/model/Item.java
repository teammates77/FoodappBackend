package com.Userservice.model;

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
public class Item {

    private Integer itemId;

    private String itemName;

    private Category category;

    private Integer quantity;

    private Double cost;

    private List<Restaurant> restaurants = new ArrayList<>();

}