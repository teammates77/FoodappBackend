package com.foodapp.foodcartservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodapp.foodcartservice.dto.ItemDTO;

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
public class Restaurant {

    private Integer restaurantId;

    private String restaurantName;

    private String addressLine;

    private String city;

    private String state;

    private String country;

    private Integer pinCode;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<ItemDTO> items = new ArrayList<>();

    private String managerName;

    private String contact;
}
