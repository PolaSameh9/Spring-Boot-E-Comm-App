package com.example.SpringEcom.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.SpringEcom.model.Category;
import com.example.SpringEcom.repo.CategoryRepo;

@Service
public class CategoryService {

    
    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo){
        this.categoryRepo = categoryRepo;
    }

    public Category addOrUpdateCategory(Category category){
        return  categoryRepo.save(category);
    }

    public Category getCategoryById(int id) {
        return categoryRepo.findById(id)
        .orElseThrow(() ->
            new RuntimeException("Category not found"));
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }


    public void deleteCategory(Category category) {
        categoryRepo.delete(category);
    }

}
