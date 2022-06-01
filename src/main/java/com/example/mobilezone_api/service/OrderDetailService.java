package com.example.mobilezone_api.service;

import com.example.mobilezone_api.dto.OrderDetailDTO;
import com.example.mobilezone_api.exception.ColorNotFoundException;
import com.example.mobilezone_api.exception.OrderDetailNotFoundException;
import com.example.mobilezone_api.exception.ProductNotFoundException;
import com.example.mobilezone_api.model.OrderDetail;
import com.example.mobilezone_api.repository.ColorRepository;
import com.example.mobilezone_api.repository.OrderDetailRepository;
import com.example.mobilezone_api.repository.OrderRepository;
import com.example.mobilezone_api.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ColorRepository colorRepository;

    @Transactional(readOnly = true)
    public List<OrderDetail> getAll() {
        return orderDetailRepository.findAll();
    }

    @Transactional
    public OrderDetail getOrderDetail(Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new OrderDetailNotFoundException("Order Detail not found with id -" + id));

        return orderDetail;
    }

    @Transactional
    public OrderDetail save(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setOrder(orderRepository.findById(orderDetailDTO.getOrderId())
                .orElseThrow(() -> new OrderDetailNotFoundException("Order Detail not found with id -" + orderDetailDTO.getOrderId())));
        orderDetail.setProduct(productRepository.findById(orderDetailDTO.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id -" + orderDetailDTO.getProductId())));
        orderDetail.setColor(colorRepository.findById(orderDetailDTO.getColorId())
                .orElseThrow(() -> new ColorNotFoundException("Color not found with id -" + orderDetailDTO.getColorId())));
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        orderDetail.setPrice(orderDetailDTO.getPrice());

        orderDetailRepository.save(orderDetail);

        return orderDetail;
    }

    @Transactional
    public void delete(Long id) {
        orderDetailRepository.deleteById(id);
    }
}
