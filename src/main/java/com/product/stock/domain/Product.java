package com.product.stock.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.util.Objects.isNull;

public record Product(
        String id,
        String name,
        String description,
        BigDecimal price,
        int quantity,
        LocalDateTime create
) {

    public static Builder builder(final String name, final String description, final BigDecimal price) {
        return new Builder(name, description, price);
    }

    public static class Builder {
        private String id;
        private final String name;
        private final String description;
        private final BigDecimal price;
        private int quantity;
        private LocalDateTime create;

        public Builder(final String name, final String description, final BigDecimal price) {
            this.name = name;
            this.description = description;
            this.price = price;
        }

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder quantity(final int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder create(final LocalDateTime create) {
            this.create = create;
            return this;
        }

        public Product build() {
            return new Product(id, name, description, price, quantity, isNull(create) ? LocalDateTime.now() : create);
        }
    }
}

