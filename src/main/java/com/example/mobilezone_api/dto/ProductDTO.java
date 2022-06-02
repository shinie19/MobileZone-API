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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPriceIn() {
        return priceIn;
    }

    public void setPriceIn(Double priceIn) {
        this.priceIn = priceIn;
    }

    public Double getPriceOut() {
        return priceOut;
    }

    public void setPriceOut(Double priceOut) {
        this.priceOut = priceOut;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public List<Long> getColorIds() {
        return colorIds;
    }

    public void setColorIds(List<Long> colorIds) {
        this.colorIds = colorIds;
    }

    public List<Long> getOrderDetailIds() {
        return orderDetailIds;
    }

    public void setOrderDetailIds(List<Long> orderDetailIds) {
        this.orderDetailIds = orderDetailIds;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getCountBuy() {
        return countBuy;
    }

    public void setCountBuy(Long countBuy) {
        this.countBuy = countBuy;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
