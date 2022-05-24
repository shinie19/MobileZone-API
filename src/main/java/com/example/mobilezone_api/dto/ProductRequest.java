package com.example.mobilezone_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String name;
    private Double priceIn;
    private Double priceOut;
    private Integer discount;
    private String images;
    private String description;
    private Long categoryId;
    private Long brandId;
    private Set<Long> colorIds;
    private Long quantity;
    private Long countBuy;
}
