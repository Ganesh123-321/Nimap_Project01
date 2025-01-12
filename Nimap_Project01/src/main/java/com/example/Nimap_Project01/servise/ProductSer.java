package com.example.Nimap_Project01.servise;


import com.example.Nimap_Project01.dto.ProductRequest;
import com.example.Nimap_Project01.entity.Category;
import com.example.Nimap_Project01.entity.Product;
import com.example.Nimap_Project01.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductSer {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategorySer categorySer;

    public Page<Product> getAllProducts(Pageable pageable)
    {
        return productRepository.findAll(pageable);
    }

    public Optional<Product> getProductByID(Long id)
    {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {

        if (product.getCategory() == null) {
            throw new RuntimeException("Category must not be null");
        }
        return productRepository.save(product);
    }

    public Product createProductWithCategory(ProductRequest productRequest) {
        Category category = categorySer.getCategoryById(productRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));


        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        System.out.println(category.getName());
        product.setCategory(category);

        createProduct(product);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updateProduct)
    {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found sir...!"));

        product.setName(updateProduct.getName());
        product.setPrice(updateProduct.getPrice());
        product.setCategory(updateProduct.getCategory());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id)
    {
        productRepository.deleteById(id);
    }
}
