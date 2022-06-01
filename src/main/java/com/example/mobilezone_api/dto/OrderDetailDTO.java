package com.example.mobilezone_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private Long id;
    private Long orderId;
    private Long productId;
    private Long colorId;
    private Integer quantity;
    private Double price;
}
