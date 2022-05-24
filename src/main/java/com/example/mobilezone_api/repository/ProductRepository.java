package com.example.mobilezone_api.repository;

import com.example.mobilezone_api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
