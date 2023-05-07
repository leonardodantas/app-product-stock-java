package com.product.stock.infra.http.requests;

import java.math.BigDecimal;

public record ProductRequest(
        String name,
        String description,
        BigDecimal price,
        int quantity
) {
}
