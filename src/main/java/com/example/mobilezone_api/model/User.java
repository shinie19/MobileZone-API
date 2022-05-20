package com.example.mobilezone_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

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

    @Column(name = "Status")
    private Boolean status;
}
