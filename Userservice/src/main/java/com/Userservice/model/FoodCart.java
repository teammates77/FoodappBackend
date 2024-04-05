package com.Userservice.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodCart {

    private Integer cartId;

    @JsonIgnore
    private User user;

    private List<Item> items = new ArrayList<>();

}