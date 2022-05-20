package com.example.mobilezone_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Category")
public class Category {
    @Id
    @Column(name = "CategoryId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "CategoryName")
    private String categoryName;

    @Column(name = "ParentId")
    private Long parentId;

    @Column(name = "MetaLink")
    private String metaLink;

    @Column(name = "DisplayNo")
    private Long displayNo;

    @Column(name = "IconImg")
    private String iconImg;

    @Column(name = "FeatureImg")
    private String featureImg;

    @Column(name = "Description")
    private String description;

    @Column(name = "ShowHome")
    private Boolean showHome;

    @Column(name = "Status")
    private Boolean status;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Product> products;
}
