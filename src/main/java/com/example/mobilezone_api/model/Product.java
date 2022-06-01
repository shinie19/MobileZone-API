package com.example.mobilezone_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Set;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @Column(name = "ProductId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "ProductName")
    private String productName;

    @Column(name = "PriceIn")
    private Double priceIn;

    @Column(name = "PriceOut")
    private Double priceOut;

    @Column(name = "Discount")
    private Integer discount;

    @Column(name = "Images")
    private String images;

    @Column(name = "Description")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "CategoryId", referencedColumnName = "CategoryId")
    private Category category;

    @ManyToOne()
    @JoinColumn(name = "BrandId", referencedColumnName = "BrandId")
    private Brand brand;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Product_Color",
            // 2 cột của bảng trung gian
            joinColumns = @JoinColumn(name = "ProductId"),
            inverseJoinColumns = @JoinColumn(name = "ColorId"))
    private Set<Color> colors;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderDetail> orderDetails;

    @Column(name = "CreateDate")
    private Instant createDate;

    @Column(name = "Quantity")
    private Long quantity;

    @Column(name = "CountBuy")
    private Long countBuy;

    @Column(name = "Status")
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Set<Color> getColors() {
        return colors;
    }

    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
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
