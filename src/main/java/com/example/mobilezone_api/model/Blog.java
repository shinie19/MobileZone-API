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
@Table(name = "Blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BlogId")
    private Long blogId;

    @Column(name = "BlogTitle")
    private String blogTitle;

    @Column(name = "Logo")
    private String logo;

    @Column(name = "Description")
    private String description;

    @Column(name = "Status")
    private Boolean status;

    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Product> products;
}
