package com.example.mobilezone_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private String authenticationToken;
    private Long userId;
    private String email;
    private Boolean isAdmin;
}
