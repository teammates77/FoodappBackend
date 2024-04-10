package com.foodapp.restaurantservice.dto;


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
	    
	    private String Restaurant_image_Url;

	    private String managerName;

	    private String contact;
	    
	    private String addressLine;

	    private String city;

	    private String state;

	    private String country;

	    private Integer pinCode;
	
}
