package com.example.mobilezone_api.controller;

import com.example.mobilezone_api.dto.OrderDTO;
import com.example.mobilezone_api.dto.OrderDetailDTO;
import com.example.mobilezone_api.model.Order;
import com.example.mobilezone_api.model.OrderDetail;
import com.example.mobilezone_api.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        return orderService.updateOrder(id, orderDTO);
    }

    @PostMapping
    public OrderDTO create(@RequestBody OrderDTO orderDTO) {
        return orderService.save(orderDTO);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }
}
