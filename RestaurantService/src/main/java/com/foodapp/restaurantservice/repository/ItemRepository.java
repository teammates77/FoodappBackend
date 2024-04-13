package com.foodapp.restaurantservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapp.restaurantservice.dto.ItemsInRestaurantDTO;
import com.foodapp.restaurantservice.model.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    public List<Item> findByCategoryId(Integer categoryId);
    
    
	public List<Item> findByRestaurant_RestaurantId(Integer restaurantId);

}
