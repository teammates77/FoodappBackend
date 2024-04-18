package com.foodapp.restaurantservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.foodapp.restaurantservice.dto.RestaurantDTO;
import com.foodapp.restaurantservice.dto.RestaurantInfoDTO;
import com.foodapp.restaurantservice.model.Restaurant;



public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

	Optional<Restaurant> findByRestaurantId(Integer restaurantId);
	
	@Query("SELECT new com.foodapp.restaurantservice.dto.RestaurantInfoDTO(r.restaurantId, r.restaurantName, r.contact, r.restaurant_image_Url, r.managerName) FROM Restaurant r")
	List<RestaurantInfoDTO> findAllRestaurants();

	Restaurant findByMerchantId(Integer merchantId);



//    @Query("SELECT new RestaurantsInItemDTO(r.restaurantId, r.restaurantName, r.managerName, r.contact) from Restaurant r INNER JOIN r.items i where i.itemId = :itemId")
//    public List<RestaurantsInItemDTO> getRestaurantsByItem(@Param("itemId") Integer itemId);

}
