package com.example.SpringEcom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringEcom.model.Category;
import com.example.SpringEcom.service.CategoryService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    private ResponseEntity<String> createCategory(@RequestBody Category category){
        Category newCategory = categoryService.createCategory(category);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }
    
    @GetMapping("/category/{id}")
    private ResponseEntity<Category> getCategoryById(@PathVariable int id){
        Category newCategory = categoryService.getCategoryById(id);
        return new ResponseEntity<>(newCategory, HttpStatus.OK);
    }

    @GetMapping("/categories")
    private ResponseEntity<List<Category>> getCategories(){
        List<Category> newCategory = categoryService.getAllCategories();
        return new ResponseEntity<>(newCategory, HttpStatus.OK);
    }


    @PutMapping("/category/{id}")
    private ResponseEntity<String> UpdateCategory(@PathVariable int id){
        
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

}
