package com.example.mobilezone_api.dto;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColorDTO {
    private Long id;
    private String name;
    private Double bonusPrice;
    private List<Long> productIds;
    private List<Long> orderDetailIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBonusPrice() {
        return bonusPrice;
    }

    public void setBonusPrice(Double bonusPrice) {
        this.bonusPrice = bonusPrice;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public List<Long> getOrderDetailIds() {
        return orderDetailIds;
    }

    public void setOrderDetailIds(List<Long> orderDetailIds) {
        this.orderDetailIds = orderDetailIds;
    }
}
