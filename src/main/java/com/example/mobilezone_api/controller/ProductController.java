package com.example.mobilezone_api.controller;

import com.example.mobilezone_api.dto.ProductDTO;
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
    public List<ProductDTO> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
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
