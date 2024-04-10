package com.foodapp.restaurantservice.dto;

import java.util.ArrayList;
import java.util.List;

import com.foodapp.restaurantservice.model.Address;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MerchantRestaurantDTO {
	
	   private Integer restaurantId;
	   
	   private Integer merchantId;

	    private String restaurantName;

	    @Embedded
	    private Address address;
	    
	    private String restaurant_image_Url;

	    private String managerName;

	    private String contact;

}
