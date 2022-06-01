package com.example.mobilezone_api.mapper;

import com.example.mobilezone_api.dto.OrderDetailDTO;
import com.example.mobilezone_api.model.OrderDetail;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

    @Mapping(expression = "java(orderDetail.getOrder().getOrderId())", target = "orderId")
    @Mapping(expression = "java(orderDetail.getProduct().getProductId())", target = "productId")
    @Mapping(expression = "java(orderDetail.getColor().getId())", target = "colorId")
    OrderDetailDTO mapToDTO(OrderDetail orderDetail);

    @InheritInverseConfiguration
    OrderDetail mapDTOToModel(OrderDetailDTO orderDetailDTO);
}
