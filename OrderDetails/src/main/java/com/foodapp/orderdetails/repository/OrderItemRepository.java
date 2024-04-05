package com.foodapp.orderdetails.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapp.orderdetails.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findByRestaurantId(Integer restaurantId);

}
