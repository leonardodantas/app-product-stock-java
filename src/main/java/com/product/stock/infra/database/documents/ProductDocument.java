package com.product.stock.infra.database.documents;

import com.product.stock.domain.Product;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "products")
public record ProductDocument(
        @Id
        String id,
        String code,
        String name,
        String description,
        BigDecimal price,
        int quantity,
        LocalDateTime create,
        LocalDateTime update,
        List<String> details
) {
    public static ProductDocument from(final Product product) {
        return new ProductDocument(
                product.id(),
                product.code(),
                product.name(),
                product.description(),
                product.price(),
                product.quantity(),
                product.create(),
                product.update(),
                product.details()
        );
    }
}
