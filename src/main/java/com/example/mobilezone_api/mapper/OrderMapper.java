package com.example.mobilezone_api.mapper;

import com.example.mobilezone_api.dto.OrderDTO;
import com.example.mobilezone_api.model.Order;
import com.example.mobilezone_api.model.OrderDetail;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(expression = "java(order.getUser().getUserId())", target = "userId")
    @Mapping(expression = "java(mapOrderDetails(order.getOrderDetails()))", target = "orderDetailIds")
    OrderDTO mapOrderToDTO(Order order);

    default List<Long> mapOrderDetails(List<OrderDetail> orderDetails) {
        List<Long> orderDetailIds = new ArrayList<>();

        for (OrderDetail o: orderDetails) {
            orderDetailIds.add(o.getOrderDetailId());
        }
        return orderDetailIds;
    }
    @InheritInverseConfiguration
    Order mapDTOToOrder(OrderDTO orderDTO);
}
