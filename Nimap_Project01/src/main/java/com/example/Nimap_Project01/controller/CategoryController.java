package com.example.Nimap_Project01.controller;


import com.example.Nimap_Project01.entity.Category;
import com.example.Nimap_Project01.servise.CategorySer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategorySer categorySer;

    @GetMapping
    public Page<Category> getAllCategories(Pageable pageable)
    {
        return categorySer.getAllCategories(pageable);
    }


    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id)
    {
        return categorySer.getCategoryById(id)
                .orElseThrow(()->new RuntimeException("That Categorie not found sir..!"));
    }

    @PostMapping()
    public Category createCategory(@RequestBody Category category)
    {
        return categorySer.createNewCategory(category);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id,@RequestBody  Category category)
    {
        return categorySer.updateCategory(id,category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id)
    {
        categorySer.deleteCategory(id);
    }

}
