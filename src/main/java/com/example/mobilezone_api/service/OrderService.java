package com.example.mobilezone_api.service;

import com.example.mobilezone_api.dto.OrderDTO;
import com.example.mobilezone_api.exception.OrderDetailNotFoundException;
import com.example.mobilezone_api.exception.OrderNotFoundException;
import com.example.mobilezone_api.mapper.OrderMapper;
import com.example.mobilezone_api.model.Order;
import com.example.mobilezone_api.model.OrderDetail;
import com.example.mobilezone_api.repository.OrderDetailRepository;
import com.example.mobilezone_api.repository.OrderRepository;
import com.example.mobilezone_api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

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
    public OrderDTO save(OrderDTO orderDTO) {
        Order order = orderMapper.mapDTOToOrder(orderDTO);
        order.setUser(userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Not found with user id-" + orderDTO.getUserId())));
        order.setCreateDate(Instant.now());
        order.setStatus(Boolean.FALSE);
        Order orderSaved = orderRepository.save(order);

        orderDTO.setOrderId(orderSaved.getOrderId());
        orderDTO.setCreateDate(orderSaved.getCreateDate());
        orderDTO.setStatus(orderSaved.getStatus());
        return orderDTO;
    }

    @Transactional
    public Order updateOrder(Long id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id -" + id));

        if (orderDTO.getUserId() != null) {
            order.setUser(userRepository.findById(orderDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id -" + orderDTO.getUserId())));
        }

        if (orderDTO.getFullName() != null) order.setFullName(orderDTO.getFullName());
        if (orderDTO.getEmail() != null) order.setEmail(orderDTO.getEmail());
        if (orderDTO.getPhone() != null) order.setPhone(orderDTO.getPhone());
        if (orderDTO.getAddress() != null) order.setAddress(orderDTO.getAddress());
        if (orderDTO.getTotal() != null) order.setTotal(orderDTO.getTotal());
        if (orderDTO.getStatus() != null) order.setStatus(orderDTO.getStatus());

        return orderRepository.save(order);
    }

    @Transactional
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

}
