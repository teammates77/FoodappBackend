package com.BillService.demo.Service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.BillService.demo.Model.Category;



@FeignClient(name = "CATEGORY-SERVICE")
public interface CategoryService {

    @GetMapping("/fooddelivery/category/{categoryId}")
    public Category getCategoryById(@PathVariable Integer categoryId);

    @GetMapping("/fooddelivery/category/name/{categoryName}")
    public Category getCategoryByName(@PathVariable String categoryName);




}