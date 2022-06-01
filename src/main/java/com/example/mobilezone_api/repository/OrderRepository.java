package com.example.mobilezone_api.repository;

import com.example.mobilezone_api.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
