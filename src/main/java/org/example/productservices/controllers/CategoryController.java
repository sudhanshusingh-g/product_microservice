package org.example.productservices.controllers;

import org.example.productservices.models.Category;
import org.example.productservices.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllCategories(){
        List<Category>categories=categoryService.getAllCategories();
        List<String> responses=new ArrayList<>();
        for(Category category:categories){
            responses.add(category.getTitle());
        }
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }


}
