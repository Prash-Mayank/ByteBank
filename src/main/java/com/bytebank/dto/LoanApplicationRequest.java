package com.bytebank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanApplicationRequest {
    @NotBlank
    private String type; // PERSONAL, HOME, EDUCATION
    @Positive
    private BigDecimal amount;
    private int tenureMonths;
}
