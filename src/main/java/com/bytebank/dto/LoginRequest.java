package com.bytebank.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank
    private String systemId;
    @NotBlank
    private String password;
}
