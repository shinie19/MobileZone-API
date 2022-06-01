package com.example.mobilezone_api.repository;

import com.example.mobilezone_api.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
