package com.example.mobilezone_api.controller;

import com.example.mobilezone_api.dto.ProductRequest;
import com.example.mobilezone_api.model.Product;
import com.example.mobilezone_api.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PostMapping
    public Product create(@RequestBody ProductRequest productRequest) {
        return productService.save(productRequest);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
