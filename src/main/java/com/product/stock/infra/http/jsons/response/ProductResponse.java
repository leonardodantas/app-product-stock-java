package com.product.stock.infra.http.jsons.response;

import com.product.stock.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponse(

        String id,
        String code,
        String name,
        String description,
        BigDecimal price,
        int quantity,
        List<String> details
) {
    public static ProductResponse from(final Product product) {
        return new ProductResponse(
                product.id(), product.code(), product.name(),
                product.description(), product.price(),
                product.quantity(), product.details());
    }
}
