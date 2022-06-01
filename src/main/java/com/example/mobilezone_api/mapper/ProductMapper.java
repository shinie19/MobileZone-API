package com.example.mobilezone_api.mapper;

import com.example.mobilezone_api.dto.ProductDTO;
import com.example.mobilezone_api.model.Color;
import com.example.mobilezone_api.model.OrderDetail;
import com.example.mobilezone_api.model.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProductMapper {

//    @Mapping(expression = "java(product.getCategory().getCategoryId())", target = "categoryId")
    @Mapping(expression = "java(product.getBrand().getBrandId())", target = "brandId")
    @Mapping(expression = "java(mapColor(product.getColors()))", target = "colorIds")
    @Mapping(expression = "java(mapOrderDetail(product.getOrderDetails()))", target = "orderDetailIds")
    ProductDTO mapProductToDTO(Product product);

    default List<Long> mapColor(Set<Color> colors) {
        List<Long> colorIds = new ArrayList<>();

        for (Color c: colors) {
            colorIds.add(c.getId());
        }
        return colorIds;
    }
    default List<Long> mapOrderDetail(List<OrderDetail> orderDetails) {
        List<Long> orderDetailIds = new ArrayList<>();

        for (OrderDetail o: orderDetails) {
            orderDetailIds.add(o.getOrderDetailId());
        }
        return orderDetailIds;
    }

    @InheritInverseConfiguration
    Product mapDTOToProduct(ProductDTO productDTO);
}
