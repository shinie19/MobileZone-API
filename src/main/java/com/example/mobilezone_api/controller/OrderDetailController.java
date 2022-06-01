package com.example.mobilezone_api.controller;

import com.example.mobilezone_api.dto.OrderDetailDTO;
import com.example.mobilezone_api.model.OrderDetail;
import com.example.mobilezone_api.service.OrderDetailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-detail")
@AllArgsConstructor
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    @GetMapping
    public List<OrderDetail> getAll() {
        return orderDetailService.getAll();
    }

    @GetMapping("/{id}")
    public OrderDetail getOrderDetail(@PathVariable Long id) {
        return orderDetailService.getOrderDetail(id);
    }

    @PostMapping
    public OrderDetail create(@RequestBody OrderDetailDTO orderDetailDTO) {
        return orderDetailService.save(orderDetailDTO);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        orderDetailService.delete(id);
    }
}
