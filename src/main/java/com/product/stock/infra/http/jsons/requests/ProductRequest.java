package com.product.stock.infra.http.jsons.requests;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @DecimalMin(value = "0.01")
        BigDecimal price,
        int quantity
) {
}
