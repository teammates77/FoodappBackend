package com.foodapp.restaurantservice.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {

    private Integer addressId;

    private String buildingName;

    private String streetName;

    private String area;

    private String city;

    private String state;

    private String country;

    private Integer pinCode;

}
