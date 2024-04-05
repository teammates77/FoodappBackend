package com.foodapp.categoryservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.categoryservice.exceptions.CategoryException;
import com.foodapp.categoryservice.model.Category;
import com.foodapp.categoryservice.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

//    @Autowired
//    CategoryRepository categoryRepository;
    private final CategoryRepository categoryRepository;
    
    private static final String CATEGORY_ID_NOT_FOUND_MESSAGE = "Category does not exists with category id : ";

    
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
    	this.categoryRepository=categoryRepository;
    }
    
    @Override
    public Category addCategory(Category category) {

        Optional<Category> categoryOpt = categoryRepository.findByCategoryName(category.getCategoryName());

        if (categoryOpt.isPresent()) throw new CategoryException("Category already present with name : "+category.getCategoryName());

        return categoryRepository.save(category);

    }

    @Override
    public Category updateCategory(Category category) {

        Category savedCategory = categoryRepository.findById(category.getCategoryId()).orElseThrow(()-> new CategoryException(CATEGORY_ID_NOT_FOUND_MESSAGE+category.getCategoryId()));

        Optional<Category> alreadyPresentCategoryOpt = categoryRepository.findByCategoryName(category.getCategoryName());

        if(alreadyPresentCategoryOpt.isPresent()) throw new CategoryException("Category already present with name : "+category.getCategoryName());

        savedCategory.setCategoryName(category.getCategoryName());

        return categoryRepository.save(savedCategory);

    }

    @Override
    public Category removeCategory(Integer categoryId) {

        Category savedCategory = categoryRepository.findById(categoryId).orElseThrow(()-> new CategoryException(CATEGORY_ID_NOT_FOUND_MESSAGE+categoryId));

        categoryRepository.delete(savedCategory);

        return savedCategory;

    }

    @Override
    public Category viewCategoryById(Integer categoryId) {

        return categoryRepository.findById(categoryId).orElseThrow(()-> new CategoryException(CATEGORY_ID_NOT_FOUND_MESSAGE+categoryId));

    }

    @Override
    public Category viewCategoryByName(String categoryName) {

        return categoryRepository.findByCategoryName(categoryName).orElseThrow(()-> new CategoryException("Category does not exists with category name : "+categoryName));

    }

    @Override
    public List<Category> viewAllCategory() {

        List<Category> categories = categoryRepository.findAll();

        if (categories.isEmpty()) throw new CategoryException("Categories not found");

        return categories;

    }
}
