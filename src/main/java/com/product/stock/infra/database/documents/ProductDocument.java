package com.product.stock.infra.database.documents;

import com.product.stock.domain.Product;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "products")
public record ProductDocument(
        @Id
        String id,
        String name,
        String description,
        BigDecimal price,
        int quantity,
        LocalDateTime create
) {
    public static ProductDocument from(final Product product) {
        return new ProductDocument(
                product.id(),
                product.name(),
                product.description(),
                product.price(),
                product.quantity(),
                product.create()
        );
    }
}
