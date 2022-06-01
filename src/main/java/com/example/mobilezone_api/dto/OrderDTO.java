package com.example.mobilezone_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private Long userId;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private Double total;
    private Instant createDate;
    private List<Long> orderDetailIds;
    private Boolean status;
}
