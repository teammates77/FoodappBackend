package com.foodapp.restaurantservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantInfoDTO {
	
	private Integer restaurantId;

    private String restaurantName;

    private String contact;
    
    private String restaurant_image_Url;
    
    private String managerName;

}
