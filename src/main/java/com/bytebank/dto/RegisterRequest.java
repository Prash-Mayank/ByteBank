package com.bytebank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String password;
    @Email
    @NotBlank
    private String email;
    private String phone;
    @NotBlank
    private String role; // ADM, MGR, CUS
}
