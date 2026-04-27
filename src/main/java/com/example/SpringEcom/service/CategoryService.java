package com.example.SpringEcom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringEcom.model.Category;
import com.example.SpringEcom.repo.CategoryRepo;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    public Category createCategory(Category category) {
        return  categoryRepo.save(category);
    }

    public Category getCategoryById(int id) {
        return categoryRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        return categories;
    }

}
