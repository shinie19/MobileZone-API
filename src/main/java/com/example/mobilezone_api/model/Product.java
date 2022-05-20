package com.example.mobilezone_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
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
    private Long discount;

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

    @Column(name = "CreateDate")
    private Date createDate;

    @Column(name = "Quantity")
    private Long quantity;

    @Column(name = "CountBuy")
    private Long countBuy;

    @Column(name = "Status")
    private Boolean status;
}
