package com.product.stock.infra.http.jsons.requests;

import java.math.BigDecimal;

public record ProductRequest(
        String name,
        String description,
        BigDecimal price,
        int quantity
) {
}
