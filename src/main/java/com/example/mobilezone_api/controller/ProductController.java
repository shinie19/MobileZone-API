package com.example.mobilezone_api.controller;

import com.example.mobilezone_api.dto.ProductDTO;
import com.example.mobilezone_api.model.Product;
import com.example.mobilezone_api.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDTO> getAll() {
        return productService.getAll();
    }

    @GetMapping("/new-arrivals")
    public List<ProductDTO> getNewArrivals() {
        return productService.getNewArrivals();
    }

    @GetMapping("/featured-products")
    public List<ProductDTO> getFeaturedProducts() {
        return productService.getFeaturedProducts();
    }

    @GetMapping("/best-seller-products")
    public List<ProductDTO> getBestSellerProducts() {
        return productService.getBestSellerProducts();
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @GetMapping("/brand/{id}")
    public List<ProductDTO> getByBrandId(@PathVariable Long id) {
        return productService.getByBrand(id);
    }

    @PostMapping
    public ProductDTO create(@RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
