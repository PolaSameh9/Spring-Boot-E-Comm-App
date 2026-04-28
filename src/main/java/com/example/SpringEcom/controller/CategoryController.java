package com.example.SpringEcom.controller;

import java.io.IOException;
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
        try{
            Category newCategory = categoryService.addOrUpdateCategory(category);
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/category/{id}")
    private ResponseEntity<Category> getCategoryById(@PathVariable int id){
        Category newCategory = categoryService.getCategoryById(id);
        if(newCategory.getId() > 0){
            return new ResponseEntity<>(newCategory, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/categories")
    private ResponseEntity<List<Category>> getCategories(){
        List<Category> newCategory = categoryService.getAllCategories();
        return new ResponseEntity<>(newCategory, HttpStatus.OK);
    }

    


    @PutMapping("/category/{id}")
    private ResponseEntity<String> UpdateCategory(@PathVariable int id, @RequestBody Category category){
        try{
            Category newCategory = categoryService.addOrUpdateCategory(category);
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        }catch(IOException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
    }


}
