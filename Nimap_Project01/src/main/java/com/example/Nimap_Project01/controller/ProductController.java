package com.example.Nimap_Project01.controller;


import com.example.Nimap_Project01.dto.ProductRequest;
import com.example.Nimap_Project01.entity.Product;
import com.example.Nimap_Project01.repo.ProductRepository;
import com.example.Nimap_Project01.servise.ProductSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductSer productSer;

    @GetMapping()
    public Page<Product> getAllProducts(Pageable pageable)
    {
        return  productSer.getAllProducts(pageable);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id)
    {
         return productSer.getProductByID(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
    }

      @PostMapping()
      public ResponseEntity<Product> saveProduct(@RequestBody ProductRequest productRequest) {
        Product createdProduct = productSer.createProductWithCategory(productRequest);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        }



    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productSer.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productSer.deleteProduct(id);
    }
}
