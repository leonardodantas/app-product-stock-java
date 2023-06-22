package com.product.stock.infra.kafka.jsons;

import com.product.stock.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public record ProductJson(
        String id,
        String code,
        String name,
        String description,
        BigDecimal price,
        List<String> details
) {
    public static ProductJson from(final Product product) {
        return new ProductJson(
                product.id(),
                product.code(),
                product.name(),
                product.description(),
                product.price(),
                product.details()
        );
    }
}
