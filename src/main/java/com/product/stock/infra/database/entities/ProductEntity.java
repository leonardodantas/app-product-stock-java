package com.product.stock.infra.database.entities;

import com.product.stock.domain.Product;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductEntity(
        @Id
        String id,
        String name,
        String description,
        BigDecimal price,
        int quantity,
        LocalDateTime create
) {
    public static ProductEntity from(final Product product) {
        return new ProductEntity(
                product.id(),
                product.name(),
                product.description(),
                product.price(),
                product.quantity(),
                product.create()
        );
    }
}
