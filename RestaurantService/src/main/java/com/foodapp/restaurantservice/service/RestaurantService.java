package com.foodapp.restaurantservice.service;

import java.util.List;

import com.foodapp.restaurantservice.dto.MerchantRestaurantDTO;
import com.foodapp.restaurantservice.dto.RestaurantDTO;
import com.foodapp.restaurantservice.dto.RestaurantInfoDTO;
import com.foodapp.restaurantservice.dto.RestaurantsInItemDTO;
import com.foodapp.restaurantservice.model.Restaurant;

public interface RestaurantService {

    public RestaurantsInItemDTO addRestaurant(RestaurantsInItemDTO restaurant);

    //public Restaurant updateRestaurant(Restaurant restaurant);

    public Restaurant removeRestaurant(Integer restaurantId);

    public RestaurantDTO viewRestaurant(Integer restaurantId);

	public List<RestaurantInfoDTO> getAllRestaurants();

	MerchantRestaurantDTO updateRestaurant(MerchantRestaurantDTO restaurantDTO);

	public MerchantRestaurantDTO getRestaurantByMerchantId(Integer merchantId);


    //public List<Restaurant> viewRestaurantByLocation(String location);

   // public List<RestaurantsInItemDTO> viewRestaurantByItem(Integer itemId);

    //public Restaurant addItemToRestaurantMenu(Integer itemId, Integer restaurantId);

}
