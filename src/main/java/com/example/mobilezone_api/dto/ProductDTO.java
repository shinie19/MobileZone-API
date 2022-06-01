package com.example.mobilezone_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long productId;
    private String productName;
    private Double priceIn;
    private Double priceOut;
    private Integer discount;
    private String images;
    private String description;
    private Long brandId;
    private List<Long> colorIds;
    private List<Long> orderDetailIds;
    private Instant createDate;
    private Long quantity;
    private Long countBuy;
    private Boolean status;
}
