package com.example.mobilezone_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

}
