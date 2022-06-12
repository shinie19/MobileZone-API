package com.example.mobilezone_api.mapper;

import com.example.mobilezone_api.dto.OrderDTO;
import com.example.mobilezone_api.dto.UserDTO;
import com.example.mobilezone_api.model.Order;
import com.example.mobilezone_api.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(expression = "java(mapOrders(user.getOrders()))", target = "orderIds")
    UserDTO mapUserToDTO(User user);

    default List<Long> mapOrders(List<Order> orders) {
        List<Long> orderIds = new ArrayList<>();

        for (Order o: orders) {
            orderIds.add(o.getOrderId());
        }
        return orderIds;
    }

    @InheritInverseConfiguration
    User mapDTOToUser(UserDTO userDTO);
}
