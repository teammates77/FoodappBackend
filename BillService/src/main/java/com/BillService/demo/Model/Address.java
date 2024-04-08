package com.BillService.demo.Model;

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
public class Address {

    private Integer addressId;

    private String buildingName;

    private String streetName;

    private String area;

    private String city;

    private String state;

    private String country;

    private Integer pinCode;

    private List<Restaurant> restaurants = new ArrayList<>();

    private User user;


    
    

}
