package com.foodapp.orderdetails.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapp.orderdetails.model.OrderDetails;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Integer> {

    public List<OrderDetails> findByCartId(Integer cartId);

	//public List<OrderDetails> findByRestaurantId(Integer restaurantId);

	


}
