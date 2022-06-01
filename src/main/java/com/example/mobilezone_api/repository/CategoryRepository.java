package com.example.mobilezone_api.repository;

import com.example.mobilezone_api.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
