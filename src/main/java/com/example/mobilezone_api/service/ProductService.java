package com.example.mobilezone_api.service;

import com.example.mobilezone_api.dto.ProductDTO;
import com.example.mobilezone_api.exception.BrandNotFoundException;
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

    @Transactional(readOnly = true)
    public List<ProductDTO> getNewArrivals() {
        return productRepository.findAllByOrderByProductIdDesc()
                .stream()
                .map(productMapper::mapProductToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getFeaturedProducts() {
        return productRepository.findAllByOrderByPriceOutDesc()
                .stream()
                .map(productMapper::mapProductToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getBestSellerProducts() {
        return productRepository.findAllByOrderByCountBuyDesc()
                .stream()
                .map(productMapper::mapProductToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductDTO save(ProductDTO productDTO) {
        Product product = productMapper.mapDTOToProduct(productDTO);

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

        productRepository.save(product);

        return productMapper.mapProductToDTO(product);
    }

    @Transactional
    public ProductDTO getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id -" + id));

        return productMapper.mapProductToDTO(product);
    }

    @Transactional
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id -" + id));

        if (productDTO.getProductName() != null)
            product.setProductName(productDTO.getProductName());
        if (productDTO.getPriceIn() != null)
            product.setPriceIn(productDTO.getPriceIn());
        if (productDTO.getPriceOut() != null)
            product.setPriceOut(productDTO.getPriceOut());
        if (productDTO.getDiscount() != null)
            product.setDiscount(productDTO.getDiscount());
        if (productDTO.getImages() != null)
            product.setImages(productDTO.getImages());
        if (productDTO.getDescription() != null)
            product.setDescription(productDTO.getDescription());
        if (productDTO.getBrandId() != null) {
            product.setBrand(brandRepository.findById(productDTO.getBrandId())
                    .orElseThrow(() -> new BrandNotFoundException("Not found with brand id-" + productDTO.getBrandId())));
        }
        if (productDTO.getColorIds() != null) {
            Set<Color> colors = new HashSet<>();
            for (int i = 0; i < productDTO.getColorIds().size(); i++) {
                Long idc = productDTO.getColorIds().get(i);
                Color c = colorRepository.findById(idc)
                        .orElseThrow(() -> new ColorNotFoundException("Not found with color id-" + idc));
                colors.add(c);
            }

            product.setColors(colors);
        }
        return productMapper.mapProductToDTO(product);
    }

    @Transactional
    public List<ProductDTO> getByBrand(Long id) {
        return productRepository.findAllByBrandBrandId(id)
                .stream()
                .map(productMapper::mapProductToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
