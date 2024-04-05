package com.foodapp.categoryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.foodapp.categoryservice.model.Category;
import com.foodapp.categoryservice.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/fooddelivery/category")
public class CategoryController {

    private final CategoryService categoryService;
    
    @Autowired
    public CategoryController(CategoryService categoryService) {
    	this.categoryService=categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category){

        Category savedCategory = categoryService.addCategory(category);

        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);

    }

    @PutMapping
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){

        Category updatedCategory = categoryService.updateCategory(category);

        return new ResponseEntity<>(updatedCategory,HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Category> removeCategory(@PathVariable Integer categoryId){

        Category removedCategory = categoryService.removeCategory(categoryId);

        return new ResponseEntity<>(removedCategory, HttpStatus.OK);

    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> viewCategory(@PathVariable Integer categoryId){

        Category category = categoryService.viewCategoryById(categoryId);

        return new ResponseEntity<>(category,HttpStatus.OK);

    }

    @GetMapping("/name/{categoryName}")
    public ResponseEntity<Category> viewCategoryByName(@PathVariable String categoryName){

        Category category = categoryService.viewCategoryByName(categoryName);

        return new ResponseEntity<>(category,HttpStatus.OK);

    }



    @GetMapping
    public ResponseEntity<List<Category>> viewAllCategory(){

        List<Category> categories = categoryService.viewAllCategory();

        return new ResponseEntity<>(categories,HttpStatus.OK);

    }

}
