package com.foodapp.categoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapp.categoryservice.model.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    public Optional<Category> findByCategoryName(String name);

}
