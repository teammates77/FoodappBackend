package com.foodapp.restaurantservice.dto;

import com.foodapp.restaurantservice.model.Address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantsInItemDTO {

    private Integer restaurantId;
    private Integer merchantId;
    private String restaurantName;

    private Address address;
    
    private String Restaurant_image_Url;

    private String managerName;

    private String contact;
   
	
}
