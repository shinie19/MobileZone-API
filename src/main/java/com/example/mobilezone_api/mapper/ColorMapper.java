package com.example.mobilezone_api.mapper;

import com.example.mobilezone_api.dto.ColorDTO;
import com.example.mobilezone_api.model.Color;
import com.example.mobilezone_api.model.OrderDetail;
import com.example.mobilezone_api.model.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ColorMapper {

    @Mapping(expression = "java(mapProducts(color.getProducts()))", target = "productIds")
    @Mapping(expression = "java(mapOrderDetail(color.getOrderDetails()))", target = "orderDetailIds")
    ColorDTO mapColorToDTO(Color color);

    default List<Long> mapProducts(List<Product> products) {
        List<Long> productIds = new ArrayList<>();

        for (Product p: products) {
            productIds.add(p.getProductId());
        }
        return productIds;
    }

    default List<Long> mapOrderDetail(List<OrderDetail> orderDetails) {
        List<Long> orderDetailIds = new ArrayList<>();

        for (OrderDetail o: orderDetails) {
            orderDetailIds.add(o.getOrderDetailId());
        }
        return orderDetailIds;
    }

    @InheritInverseConfiguration
    Color mapDTOToColor(ColorDTO colorDTO);
}
