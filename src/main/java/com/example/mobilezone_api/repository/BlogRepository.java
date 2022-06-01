package com.example.mobilezone_api.repository;

import com.example.mobilezone_api.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
