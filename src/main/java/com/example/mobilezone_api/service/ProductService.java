package com.example.mobilezone_api.service;

import com.example.mobilezone_api.dto.ProductRequest;
import com.example.mobilezone_api.exception.ColorNotFoundException;
import com.example.mobilezone_api.exception.ProductNotFoundException;
import com.example.mobilezone_api.model.Color;
import com.example.mobilezone_api.model.Product;
import com.example.mobilezone_api.repository.BrandRepository;
import com.example.mobilezone_api.repository.ColorRepository;
import com.example.mobilezone_api.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final ColorRepository colorRepository;

    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Transactional
    public Product save(ProductRequest productRequest) {
        Product product = new Product();
        product.setProductName(productRequest.getName());
        product.setPriceIn(productRequest.getPriceIn());
        product.setPriceOut(productRequest.getPriceOut());
        product.setDiscount(productRequest.getDiscount());
        product.setImages(productRequest.getImages());
        product.setDescription(productRequest.getDescription());
        product.setBrand(brandRepository.findById(productRequest.getBrandId())
                .orElseThrow(() -> new ProductNotFoundException("Brand id not found!")));

        Set<Color> colors = new HashSet<>();
        for (Iterator<Long> it = productRequest.getColorIds().iterator(); it.hasNext(); ) {
            Long id = it.next();
            colors.add(colorRepository.findById(id).orElseThrow(() -> new ColorNotFoundException("Color not found!")));
        }
        product.setColors(colors);
        product.setCreateDate(Instant.now());
        product.setStatus(Boolean.TRUE);

        return productRepository.save(product);
    }

    @Transactional
    public Product getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id -" + id));

        return product;
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
