package com.foodapp.orderdetails.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapp.orderdetails.model.OrderDetails;
import com.foodapp.orderdetails.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findByRestaurantId(Integer restaurantId);
	public List<OrderItem> findByUserId(Integer userId);
	Optional<OrderItem> findByItemIdAndRestaurantId(Integer itemId, Integer restaurantId);
	
	Optional<OrderItem> findByItemId(Integer itemId);


}
