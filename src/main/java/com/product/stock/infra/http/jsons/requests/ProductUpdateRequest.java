package com.product.stock.infra.http.jsons.requests;

import com.product.stock.infra.http.annotations.DetailsValid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.List;

public record ProductUpdateRequest(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @DecimalMin(value = "0.01")
        BigDecimal price,
        int quantity,
        @DetailsValid
        List<String> details
) {
}
