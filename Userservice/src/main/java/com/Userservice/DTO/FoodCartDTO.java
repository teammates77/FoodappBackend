package com.Userservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import com.Userservice.model.Item;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodCartDTO {

    private Integer cartId;

    private Integer userId;

    private List<Item> items = new ArrayList<>();

}