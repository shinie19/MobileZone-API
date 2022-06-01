package com.example.mobilezone_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OrderDetail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderDetailId")
    private Long orderDetailId;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "OrderId", referencedColumnName = "OrderId")
    private Order order;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "ProductId", referencedColumnName = "ProductId")
    private Product product;

//    @ManyToOne()
//    @JoinColumn(name = "MemoryId", referencedColumnName = "MemoryId")
//    private Memory memory;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "ColorId", referencedColumnName = "ColorId")
    private Color color;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "Price")
    private Double price;

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
