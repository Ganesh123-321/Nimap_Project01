package com.example.Nimap_Project01.servise;

import com.example.Nimap_Project01.entity.Category;
import com.example.Nimap_Project01.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategorySer {


    @Autowired
    private CategoryRepository categoryRepository;


    public Page<Category> getAllCategories(Pageable pageable)
    {
        return categoryRepository.findAll(pageable);
    }

    public Optional<Category> getCategoryById(Long id)
    {
        return categoryRepository.findById(id);
    }

    public Category createNewCategory(Category category)
    {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category updateCategory)
    {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Category not found..!"));

        category.setName(updateCategory.getName());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id)
    {
        categoryRepository.deleteById(id);
    }

}
