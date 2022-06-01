package com.example.mobilezone_api.service;

import com.example.mobilezone_api.dto.OrderDTO;
import com.example.mobilezone_api.exception.OrderDetailNotFoundException;
import com.example.mobilezone_api.exception.OrderNotFoundException;
import com.example.mobilezone_api.model.Order;
import com.example.mobilezone_api.model.OrderDetail;
import com.example.mobilezone_api.repository.OrderDetailRepository;
import com.example.mobilezone_api.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final AuthService authService;

    @Transactional(readOnly = true)
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Transactional
    public Order getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id -" + id));

        return order;
    }

    @Transactional
    public Order save(OrderDTO orderDTO) {
        Order order = new Order();

        order.setUser(authService.getCurrentUser());
        order.setFullName(orderDTO.getFullName());
        order.setEmail(orderDTO.getEmail());
        order.setPhone(orderDTO.getPhone());
        order.setAddress(orderDTO.getAddress());
        order.setTotal(orderDTO.getTotal());

        List<OrderDetail> orderDetails = new ArrayList<>();
        for (Long id: orderDTO.getOrderDetailIds()) {
            orderDetails.add(orderDetailRepository.findById(id).
                    orElseThrow(() -> new OrderDetailNotFoundException("Order Detail not found with id -" + id)));
        }

        order.setOrderDetails(orderDetails);
        order.setCreateDate(Instant.now());
        order.setStatus(Boolean.TRUE);

        orderRepository.save(order);

        return order;
    }

    @Transactional
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

}