package com.example.mobilezone_api.service;

import com.example.mobilezone_api.dto.ProductDTO;
import com.example.mobilezone_api.exception.ColorNotFoundException;
import com.example.mobilezone_api.exception.ProductNotFoundException;
import com.example.mobilezone_api.mapper.ProductMapper;
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
    private final ProductMapper productMapper;

    @Transactional(readOnly = true)
    public List<ProductDTO> getAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::mapProductToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public Product save(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setPriceIn(productDTO.getPriceIn());
        product.setPriceOut(productDTO.getPriceOut());
        product.setDiscount(productDTO.getDiscount());
        product.setImages(productDTO.getImages());
        product.setDescription(productDTO.getDescription());
        product.setBrand(brandRepository.findById(productDTO.getBrandId())
                .orElseThrow(() -> new ProductNotFoundException("Brand id not found!")));

        Set<Color> colors = new HashSet<>();
        for (Iterator<Long> it = productDTO.getColorIds().iterator(); it.hasNext(); ) {
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
