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
public class Restaurant {

    private Integer restaurantId;

    private String restaurantName;
    
    private String addressLine;
    
    private String city;
    
    private String state;
    
    private String country;
    
    private Integer pinCode;

    private List<Item> items = new ArrayList<>();

    private String managerName;

    private String contact;
}