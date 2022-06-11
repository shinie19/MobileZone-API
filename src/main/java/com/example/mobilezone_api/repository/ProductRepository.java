package com.example.mobilezone_api.repository;

import com.example.mobilezone_api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findAllByOrderByProductIdDesc();
    public List<Product> findAllByOrderByPriceOutDesc();
    public List<Product> findAllByOrderByCountBuyDesc();
    public List<Product> findAllByBrandBrandId(Long id);
}
