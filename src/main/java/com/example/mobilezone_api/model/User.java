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
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private Long userId;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "FullName")
    private String fullName;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Avatar")
    private String avatar;

    @Column(name = "IsAdmin")
    private Boolean isAdmin;

    @Column(name = "CodeConfirm")
    private String codeConfirm;

    @Column(name = "CreateDate")
    private Instant createDate;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> orders;

    @Column(name = "Status")
    private Boolean status;
}
