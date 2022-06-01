package com.example.mobilezone_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Color")
public class Color {
    @Id
    @Column(name = "ColorId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ColorName")
    private String name;

    @Column(name = "BonusPrice")
    private Double bonusPrice;

    @ManyToMany(mappedBy = "colors")
    private Set<Product> products;

    @OneToMany(mappedBy = "color", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderDetail> orderDetails;

}
