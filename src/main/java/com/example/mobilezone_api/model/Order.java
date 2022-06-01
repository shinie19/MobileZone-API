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
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderId")
    private Long orderId;

    @ManyToOne()
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    private User user;

    @Column(name = "FullName")
    private String fullName;

    @Column(name = "Email")
    private String email;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Address")
    private String address;

    @Column(name = "Total")
    private Double total;

    @Column(name = "CreateDate")
    private Instant createDate;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderDetail> orderDetails;

    @Column(name = "Status")
    private Boolean status;
}
