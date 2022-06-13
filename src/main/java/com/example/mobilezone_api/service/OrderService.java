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
    public List<OrderDTO> getAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::mapOrderToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderDTO getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id -" + id));

        return orderMapper.mapOrderToDTO(order);
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
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id -" + id));

        order.setUser(userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id -" + orderDTO.getUserId())));
        order.setFullName(orderDTO.getFullName());
        order.setEmail(orderDTO.getEmail());
        order.setPhone(orderDTO.getPhone());
        order.setAddress(orderDTO.getAddress());
        order.setTotal(orderDTO.getTotal());
        order.setStatus(orderDTO.getStatus());

        Order orderSaved = orderRepository.save(order);

        orderDTO.setOrderId(orderSaved.getOrderId());
        return orderDTO;
    }

    @Transactional
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

}
