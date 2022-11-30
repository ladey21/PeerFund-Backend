package com.example.funds.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private Long id;
    private String email;
    private String roles;
    private String message;

    public LoginResponse(Long id, String email, String roles) {
        this.id = id;
        this.email = email;
        this.roles = roles;
    }

    public LoginResponse(String message) {
        this.message = message;
    }
}
