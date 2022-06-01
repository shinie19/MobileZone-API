package com.example.mobilezone_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
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

    @ManyToOne()
    @JoinColumn(name = "CategoryId", referencedColumnName = "CategoryId")
    private Category category;

    @Column(name = "BlogContent")
    private String content;

    @ManyToOne()
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    private User user;

    @Column(name = "Description")
    private String description;

    @Column(name = "FeatureImg")
    private String featureImage;

    @Column(name = "CreateDate")
    private Instant createDate;

    @Column(name = "Status")
    private Boolean status;
}
