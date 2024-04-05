package com.foodapp.foodcartservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapp.foodcartservice.model.FoodCart;

import java.util.Optional;

public interface FoodCartRepository extends JpaRepository<FoodCart,Integer> {

    public Optional<FoodCart> findByUserId(Integer userId);

}
