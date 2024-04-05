package com.foodapp.categoryservice.service;

import java.util.List;

import com.foodapp.categoryservice.model.Category;

public interface CategoryService {

    public Category addCategory(Category category);

    public Category updateCategory(Category category);

    public Category removeCategory(Integer categoryId);

    public Category viewCategoryById(Integer categoryId);

    public Category viewCategoryByName(String categoryName);

    public List<Category> viewAllCategory();

}
