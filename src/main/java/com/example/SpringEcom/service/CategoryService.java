package com.example.SpringEcom.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringEcom.model.Category;
import com.example.SpringEcom.repo.CategoryRepo;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    public Category addOrUpdateCategory(Category category)  throws IOException{
        return  categoryRepo.save(category);
    }

    public Category getCategoryById(int id) {
        return categoryRepo.findById(id).orElse(null);
    }

    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        return categories;
    }


    public void deleteCategory(Category category) {
        categoryRepo.delete(category);
    }

}
